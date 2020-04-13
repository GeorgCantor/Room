package com.example.notes.di

import com.example.notes.model.db.TasksDatabase
import com.example.notes.repo.DbRepository
import com.example.notes.view.fragment.main.MainViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel(get())
    }
}

val databaseModule = module {
    single { TasksDatabase.getInstance(androidApplication()) }
    single { TasksDatabase.getInstance(androidApplication()).getTaskListDao() }
    single { TasksDatabase.getInstance(androidApplication()).getTaskDao() }
    single { TasksDatabase.getInstance(androidApplication()).getSubtaskDao() }
}

val repositoryModule = module {
    single { DbRepository(get(), get(), get()) }
}
