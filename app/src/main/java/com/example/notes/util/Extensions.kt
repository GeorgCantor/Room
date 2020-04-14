package com.example.notes.util

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.notes.R
import com.example.notes.util.Constants.ARG_SELECTED
import kotlinx.android.synthetic.main.dialog_one_button.*

fun Context.shortToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Context.showOneButtonDialog(
    title: String,
    message: String,
    function: () -> (Unit),
    function2: () -> (Unit)
) {
    val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_one_button, null)
    val builder = AlertDialog.Builder(this).setView(dialogView)
    val alertDialog = builder.show()
    alertDialog.setOnCancelListener { function2() }

    with(alertDialog) {
        this.title.text = title
        this.message.text = message
        ok_button.setOnClickListener {
            dismiss()
            function()
        }
    }
}

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

fun Context.saveSelectedListId(id: Int) = PreferenceManager(this).saveInt(ARG_SELECTED, id)

fun Context.getSelectedListId(): Int = PreferenceManager(this).getInt(ARG_SELECTED) ?: 0

fun getRandomId() = (0..999999).random()