package com.learning.talentaisproject.myUtil

import android.content.Context
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

fun Context.showToast(value : String){
    Toast.makeText(this, value , Toast.LENGTH_LONG).show()
}
fun TextInputLayout.validateNotEmpty(errorMessage: String): Boolean {
    val text = (editText as? TextInputEditText)?.text?.toString()
    return if (text.isNullOrEmpty()) {
        error = errorMessage
        false
    } else {
        error = null
        true
    }
}

fun TextInputLayout.validatePassword(minLength: Int = 6): Boolean {
    val password = (editText as? TextInputEditText)?.text?.toString()
    return when {
        password.isNullOrEmpty() -> {
            error = "Password is required"
            false
        }
        password.length < minLength -> {
            error = "Password must be at least $minLength characters"
            false
        }
        else -> {
            error = null
            true
        }
    }
}

fun TextInputLayout.validateConfirmPassword(password: String): Boolean {
    val confirmPassword = (editText as? TextInputEditText)?.text?.toString()
    return when {
        confirmPassword.isNullOrEmpty() -> {
            error = "Please confirm your password"
            false
        }
        confirmPassword != password -> {
            error = "Passwords don't match"
            false
        }
        else -> {
            error = null
            true
        }
    }
}
