package com.example.notes.view.fragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notes.db.entity.TaskList
import com.example.notes.repo.DbRepository
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val repository: DbRepository) : ViewModel() {

    val error = MutableLiveData<String>()
    val taskList = MutableLiveData<TaskList>()
    val taskLists = MutableLiveData<MutableList<TaskList>>()

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
}