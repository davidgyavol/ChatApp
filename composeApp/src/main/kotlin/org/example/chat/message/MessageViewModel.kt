package org.example.chat.message

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.example.chat.domain.model.message.Message
import org.example.chat.domain.model.user.User
import org.example.chat.domain.usecase.message.ObserveMessagesUseCase
import org.example.chat.domain.usecase.message.SendMessageUseCase
import org.example.chat.domain.usecase.user.GetOtherUserUseCase
import org.example.chat.domain.usecase.user.ObserveCurrentUserUseCase
import org.example.chat.domain.usecase.user.SwitchUserUseCase
import org.example.chat.helper.toSectionLabel

@OptIn(ExperimentalCoroutinesApi::class)
class MessageViewModel(
    private val observeMessagesUseCase: ObserveMessagesUseCase,
    private val getOtherUserUseCase: GetOtherUserUseCase,
    private val switchUserUseCase: SwitchUserUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
    observeCurrentUserUseCase: ObserveCurrentUserUseCase
) : ViewModel() {

    private val _messages = MutableStateFlow<List<ChatListItem>>(emptyList())
    val messages: StateFlow<List<ChatListItem>> = _messages.asStateFlow()

    private val _otherUser = MutableStateFlow(getOtherUserUseCase())
    val otherUser: StateFlow<User> = _otherUser.asStateFlow()

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e("MessageViewModel", "Coroutine error", throwable)
    }

    init {
        observeCurrentUserUseCase()
            .flatMapLatest { currentUser ->
                val otherUser = getOtherUserUseCase()
                _otherUser.value = otherUser

                observeMessagesUseCase(otherUser.id)
                    .map { messages ->
                        messages.toChatListItems(currentUser.id)
                    }
            }
            .onEach { _messages.value = it }
            .catch { throwable -> Log.e("MessageViewModel", "Flow error", throwable) }
            .launchIn(viewModelScope)
    }

    fun onSendClick(message: String) {
        viewModelScope.launch(errorHandler) {
            sendMessageUseCase(otherUser.value.id, message)
        }
    }

    fun onSwitchUsers() {
        viewModelScope.launch(errorHandler) {
            switchUserUseCase()
        }
    }

    private fun List<Message>.toChatListItems(currentUserId: String): List<ChatListItem> {
        val result = mutableListOf<ChatListItem>()

        forEachIndexed { index, message ->
            val prev = getOrNull(index - 1)
            if (prev == null || message.sentAt - prev.sentAt > SECTION_HEADER_THRESHOLD_MS) {
                result.add(
                    ChatListItem.SectionHeader(
                        label = message.sentAt.toSectionLabel(),
                        sentAt = message.sentAt
                    )
                )
            }
            val next = getOrNull(index + 1)
            val isSmallSpacing = next != null &&
                    next.senderUserId == message.senderUserId &&
                    next.sentAt - message.sentAt < SMALL_SPACING_THRESHOLD_MS

            result.add(
                ChatListItem.MessageItem(
                    message = ChatMessage(
                        id = message.id,
                        text = message.text,
                        isFromCurrentUser = message.senderUserId == currentUserId,
                        sentAt = message.sentAt
                    ),
                    isSmallSpacing = isSmallSpacing
                )
            )
        }
        return result
    }

    sealed class ChatListItem {
        data class MessageItem(
            val message: ChatMessage,
            val isSmallSpacing: Boolean
        ) : ChatListItem()

        data class SectionHeader(
            val label: String,
            val sentAt: Long
        ) : ChatListItem()
    }

    data class ChatMessage(
        val id: Long,
        val text: String,
        val isFromCurrentUser: Boolean,
        val sentAt: Long
    )

    private companion object {
        private const val SECTION_HEADER_THRESHOLD_MS = 3_600_000L
        private const val SMALL_SPACING_THRESHOLD_MS = 20_000L
    }
}