package org.example.chat.data.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module

@Retention(AnnotationRetention.RUNTIME)
annotation class IoDispatcher

@Retention(AnnotationRetention.RUNTIME)
annotation class MainDispatcher

@Retention(AnnotationRetention.RUNTIME)
annotation class DefaultDispatcher

val dispatcherModule = module {
    single<CoroutineDispatcher>(qualifier = qualifier<IoDispatcher>()) { Dispatchers.IO }
    single<CoroutineDispatcher>(qualifier = qualifier<MainDispatcher>()) { Dispatchers.Main }
    single<CoroutineDispatcher>(qualifier = qualifier<DefaultDispatcher>()) { Dispatchers.Default }
}