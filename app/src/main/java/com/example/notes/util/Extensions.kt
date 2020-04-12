package com.example.notes.util

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun Context.shortToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

inline fun <T> LiveData<T>.observe(
    owner: LifecycleOwner,
    crossinline observer: (T) -> Unit
) {
    this.observe(owner, Observer { it?.apply(observer) })
}

fun Activity.showKeyBoard() {
    val view = currentFocus
    val methodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    methodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
}