package com.example.notes.view.fragment.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notes.model.CommonTask
import com.example.notes.model.db.entity.Subtask
import com.example.notes.model.db.entity.Task
import com.example.notes.model.db.entity.TaskList
import com.example.notes.repo.DbRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val repository: DbRepository) : ViewModel() {

    val error = MutableLiveData<String>()
    val taskList = MutableLiveData<TaskList>()
    val taskLists = MutableLiveData<MutableList<TaskList>>()
    val tasks = MutableLiveData<MutableList<CommonTask>>()

    init {
        Observable.fromCallable {
            repository.getTaskLists()
                .subscribe({
                    taskLists.postValue(it)
                }, {
                    error.postValue(it.message)
                })
        }
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    fun getTaskListById(id: Int) {
        Observable.fromCallable {
            repository.getTaskListById(id)
                .subscribe({
                    taskList.postValue(it)
                }, {
                    error.postValue(it.message)
                })
        }
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    fun getCommonTasksById(taskListId: Int) {
        Observable.fromCallable {
            repository.getTasksFromList(taskListId)
                .subscribe({
                    val commonTasks = mutableListOf<CommonTask>()

                    it.map {
                        commonTasks.add(
                            CommonTask(
                                id = it.id,
                                taskListId = it.taskListId,
                                taskName = it.taskName,
                                taskDetails = it.taskDetails,
                                taskDate = it.taskDate,
                                taskCompleted = it.taskCompleted,
                                isSubtask = false
                            )
                        )
                    }

                    commonTasks.map {
                        repository.findSubtasksFromTask(it.id).subscribe({
                            it.map {
                                commonTasks.add(
                                    CommonTask(
                                        id = it.taskId,
                                        taskListId = it.taskId,
                                        taskName = it.subtaskName,
                                        taskDetails = it.subtaskName,
                                        taskDate = null,
                                        taskCompleted = it.taskCompleted,
                                        isSubtask = true
                                    )
                                )
                            }
                        }, {
                            error.postValue(it.message)
                        })
                    }

                    tasks.postValue(commonTasks)
                }, {
                    error.postValue(it.message)
                })
        }
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    fun insertTaskList(taskList: TaskList) {
        Observable.fromCallable {
            repository.insert(taskList)
                .subscribe({
                }, {
                    error.postValue(it.message)
                })
        }
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    fun deleteTaskList(taskList: TaskList) {
        Observable.fromCallable {
            repository.delete(taskList)
        }
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    fun insertTask(task: Task) {
        Observable.fromCallable {
            repository.insert(task)
        }
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    fun insertSubtask(subtask: Subtask) {
        Observable.fromCallable {
            repository.insert(subtask)
        }
            .subscribeOn(Schedulers.io())
            .subscribe()
    }
}