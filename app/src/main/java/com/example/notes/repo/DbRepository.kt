package com.example.notes.repo

import com.example.notes.model.db.dao.SubtaskDao
import com.example.notes.model.db.dao.TaskDao
import com.example.notes.model.db.dao.TaskListDao
import com.example.notes.model.db.entity.Subtask
import com.example.notes.model.db.entity.Task
import com.example.notes.model.db.entity.TaskList

class DbRepository(
    private val taskListDao: TaskListDao,
    private val taskDao: TaskDao,
    private val subtaskDao: SubtaskDao
) {

    fun getTaskListById(id: Int) = taskListDao.getTaskListById(id)

    fun getTaskLists() = taskListDao.getTaskLists()

    fun insert(taskList: TaskList) = taskListDao.insert(taskList)

    fun delete(taskList: TaskList) = taskListDao.delete(taskList)


    fun insert(task: Task) = taskDao.insert(task)

    fun deleteTaskById(id: Int) = taskDao.deleteById(id)

    fun getTasksFromList(taskListId: Int) = taskDao.getTasksFromList(taskListId)

    fun getAllTasks() = taskDao.getAllTasks()


    fun findSubtasksFromTask(taskId: Int) = subtaskDao.findSubtasksFromTask(taskId)

    fun insert(subtask: Subtask) = subtaskDao.insert(subtask)

    fun deleteById(id: Int) = subtaskDao.deleteById(id)
}