package com.example.notes.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.notes.R
import com.example.notes.db.dao.SubtaskDao
import com.example.notes.db.dao.TaskDao
import com.example.notes.db.dao.TaskListDao
import com.example.notes.db.entity.Subtask
import com.example.notes.db.entity.Task
import com.example.notes.db.entity.TaskList
import java.util.concurrent.Executors

@Database(entities = [TaskList::class, Task::class, Subtask::class], version = 1)
@TypeConverters(value = [DateConverter::class])
abstract class TasksDatabase : RoomDatabase() {

    abstract fun getTaskDao(): TaskDao
    abstract fun getTaskListDao(): TaskListDao
    abstract fun getSubtaskDao(): SubtaskDao

    companion object {
        private const val FIRST_ITEM_ID = 1
        private val TAG = TasksDatabase::class.java.simpleName

        private const val databaseName = "tasks.db"

        @Volatile
        private var instance: TasksDatabase? = null

        fun getInstance(context: Context): TasksDatabase {
            return instance ?: synchronized(this) {
                instance = buildPrePopulatedDatabase(context)
                instance ?: throw IllegalAccessException("Can't instantiate class $TAG")
            }
        }

        private fun buildPrePopulatedDatabase(context: Context): TasksDatabase =
            Room.databaseBuilder(context, TasksDatabase::class.java, databaseName)
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)

                        val defaultTaskListName = context.getString(R.string.my_tasks)

                        Executors.newSingleThreadScheduledExecutor().execute {
                            getInstance(context).getTaskListDao()
                                .insert(TaskList(FIRST_ITEM_ID, defaultTaskListName))
                        }
                    }

                })
                .build()
    }
}