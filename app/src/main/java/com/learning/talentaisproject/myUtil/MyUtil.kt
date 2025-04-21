package com.learning.talentaisproject.myUtil

import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.learning.talentaisproject.databinding.DialogEditStatusBinding

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

// Add this in a new file like Extensions.kt or directly in HomeActivity
fun AppCompatActivity.showEditDialog(
    currentText: String,
    onSave: (String) -> Unit
) {
    val dialogBinding = DialogEditStatusBinding.inflate(layoutInflater)
    dialogBinding.etEditStatus.setText(currentText)

    AlertDialog.Builder(this)
        .setView(dialogBinding.root)
        .setPositiveButton("Save") { _, _ ->
            val newText = dialogBinding.etEditStatus.text.toString().trim()
            if (newText.isNotEmpty()) {
                onSave(newText)
            } else {
                showToast("Status cannot be empty!")
            }
        }
        .setNegativeButton("Cancel", null)
        .show()
}
