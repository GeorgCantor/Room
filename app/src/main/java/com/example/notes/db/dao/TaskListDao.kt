package com.example.notes.db.dao

import androidx.room.*
import com.example.notes.db.entity.TaskList
import io.reactivex.Flowable

@Dao
interface TaskListDao {

    @Query("SELECT * FROM TaskList WHERE id = :id")
    fun getTaskListById(id: Int): Flowable<TaskList>

    @Query("SELECT * FROM TaskList")
    fun getTaskLists(): Flowable<List<TaskList>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(taskList: TaskList): Long

    @Delete
    fun delete(taskList: TaskList)
}