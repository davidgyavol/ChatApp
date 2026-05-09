package org.example.chat.helper

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private const val DAY_TIME_FORMAT = "EEEE HH:mm"

fun Long.toSectionLabel(): String =
    SimpleDateFormat(DAY_TIME_FORMAT, Locale.getDefault()).format(Date(this))