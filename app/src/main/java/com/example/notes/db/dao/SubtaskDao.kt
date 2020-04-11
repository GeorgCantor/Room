package com.example.notes.db.dao

import androidx.room.*
import com.example.notes.db.entity.Subtask
import io.reactivex.Flowable

@Dao
interface SubtaskDao {

    @Query("SELECT * FROM Subtask WHERE task_id = :taskId")
    fun findSubtasksFromTask(taskId: Int): Flowable<List<Subtask>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(subtask: Subtask)

    @Delete
    fun delete(subtask: Subtask)
}