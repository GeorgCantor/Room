package com.example.notes.util

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager(context: Context) {

    companion object {
        private const val MAIN_STORAGE = "main_storage"
    }

    private val prefs: SharedPreferences = context.getSharedPreferences(MAIN_STORAGE, Context.MODE_PRIVATE)

    fun saveString(key: String, value: String) = prefs.edit().putString(key, value).apply()

    fun getString(key: String): String? = prefs.getString(key, "")

    fun saveInt(key: String, value: Int) = prefs.edit().putInt(key, value).apply()

    fun getInt(key: String): Int? = prefs.getInt(key, 0)
}