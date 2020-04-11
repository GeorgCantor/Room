package com.example.notes.repo

import com.example.notes.db.dao.SubtaskDao
import com.example.notes.db.dao.TaskDao
import com.example.notes.db.dao.TaskListDao

class DbRepository(
    taskListDao: TaskListDao,
    taskDao: TaskDao,
    subtaskDao: SubtaskDao
) {
}