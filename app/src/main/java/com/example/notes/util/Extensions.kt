package com.example.notes.util

import android.animation.Animator
import android.app.Activity
import android.content.Context
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.airbnb.lottie.LottieAnimationView
import com.example.notes.util.Constants.ARG_SELECTED
import com.google.android.material.snackbar.Snackbar

fun Context.shortToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun View.showSnackbar(
    message: String,
    buttonText: String,
    funcRemove: () -> Unit,
    funcRecover: () -> (Unit)
) {
    var isCancelPressed = false
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    snackbar.setAction(buttonText) {
        funcRecover()
        isCancelPressed = true
    }
    snackbar.addCallback(object : Snackbar.Callback() {
        override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
            if (!isCancelPressed) funcRemove()
        }
    })
    snackbar.show()
}

fun View.hideKeyboard() {
    val manager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    manager.hideSoftInputFromWindow(windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
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

fun LottieAnimationView.showSingleAnimation() {
    visibility = VISIBLE
    playAnimation()
    repeatCount = 0
    speed = 2F
    addAnimatorListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(p0: Animator?) {
        }

        override fun onAnimationEnd(p0: Animator?) {
            visibility = GONE
        }

        override fun onAnimationCancel(p0: Animator?) {
        }

        override fun onAnimationStart(p0: Animator?) {
        }
    })
}