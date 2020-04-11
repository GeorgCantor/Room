package com.example.notes.db.dao

import androidx.room.*
import com.example.notes.db.entity.Task
import io.reactivex.Flowable

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: Task)

    @Delete
    fun delete(task: Task)

    @Query("SELECT * FROM Task WHERE task_list_id = :taskListId")
    fun getTasksFromList(taskListId: Int): Flowable<List<Task>>

    @Query("SELECT * FROM Task")
    fun getAllTasks(): Flowable<List<Task>>
}