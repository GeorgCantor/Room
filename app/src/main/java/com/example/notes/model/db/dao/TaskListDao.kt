package com.example.notes.model.db.dao

import androidx.room.*
import com.example.notes.model.db.entity.TaskList
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface TaskListDao {

    @Query("SELECT * FROM TaskList WHERE id = :id")
    fun getTaskListById(id: Int): Flowable<TaskList>

    @Query("SELECT * FROM TaskList")
    fun getTaskLists(): Flowable<MutableList<TaskList>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(taskList: TaskList): Completable

    @Delete
    fun delete(taskList: TaskList)
}