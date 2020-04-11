package com.example.notes.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.notes.db.entity.Subtask
import com.example.notes.db.entity.Task
import com.example.notes.db.entity.TaskList

@Database(entities = [TaskList::class, Task::class, Subtask::class], version = 1)
@TypeConverters(value = [DateConverter::class])
abstract class TasksDatabase : RoomDatabase() {
}