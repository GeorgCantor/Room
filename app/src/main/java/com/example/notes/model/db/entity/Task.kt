package com.example.notes.model.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = TaskList::class,
            parentColumns = ["id"],
            childColumns = ["task_list_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Task(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "task_list_id")
    val taskListId: Int,

    @ColumnInfo(name = "task_name")
    val taskName: String,

    @ColumnInfo(name = "task_details")
    val taskDetails: String = "",

    @ColumnInfo(name = "task_date")
    val taskDate: Date? = null,

    @ColumnInfo(name = "completed")
    val taskCompleted: Boolean = false
)