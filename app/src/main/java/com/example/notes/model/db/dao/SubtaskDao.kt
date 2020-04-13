package com.example.notes.model.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.notes.model.db.entity.Subtask
import io.reactivex.Flowable

@Dao
interface SubtaskDao {

    @Query("SELECT * FROM Subtask WHERE task_id = :taskId")
    fun findSubtasksFromTask(taskId: Int): Flowable<List<Subtask>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(subtask: Subtask)

    @Query("Delete FROM Subtask where id LIKE  :id")
    fun deleteById(id: Int)
}