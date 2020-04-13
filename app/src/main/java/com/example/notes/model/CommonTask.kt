package com.example.notes.model

import java.util.*

data class CommonTask(
    val id: Int = 0,
    val taskListId: Int,
    val taskName: String,
    val taskDetails: String = "",
    val taskDate: Date? = null,
    val taskCompleted: Boolean = false,
    val isSubtask: Boolean = false
)