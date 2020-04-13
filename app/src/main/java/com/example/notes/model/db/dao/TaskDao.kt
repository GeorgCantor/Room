package com.example.notes.model.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.notes.model.db.entity.Task
import io.reactivex.Flowable

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: Task)

    @Query("Delete FROM Task where id LIKE  :id")
    fun deleteById(id: Int)

    @Query("SELECT * FROM Task WHERE task_list_id = :taskListId")
    fun getTasksFromList(taskListId: Int): Flowable<List<Task>>

    @Query("SELECT * FROM Task")
    fun getAllTasks(): Flowable<List<Task>>
}