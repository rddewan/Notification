package com.richarddewan.notificationapp.util

import androidx.core.app.Person

data class AppMessage(
    val text: String?,
    val timeStamp: Long,
    val person: Person?
)
