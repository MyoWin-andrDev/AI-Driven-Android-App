package com.learning.talentaisproject.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.learning.talentaisproject.databinding.ActivityRegisterBinding
import com.learning.talentaisproject.myUtil.showToast
import com.learning.talentaisproject.myUtil.validateConfirmPassword
import com.learning.talentaisproject.myUtil.validateNotEmpty
import com.learning.talentaisproject.myUtil.validatePassword

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            btnSubmit.setOnClickListener {
                if (validateInputs()) {
                    // All inputs are valid, proceed with registration
                   showToast(etUsername.text.toString())
                }
            }
        }
    }
    private fun validateInputs(): Boolean {
        binding.apply {
            val isUsernameValid = usernameTextInputLayout.validateNotEmpty("Username is required")
            val isPasswordValid = passwordTextInputLayout.validatePassword()
            val isConfirmValid = confirmPasswordTextInputLayout.validateConfirmPassword(
                etPassword.text.toString()
            )
            return isUsernameValid && isPasswordValid && isConfirmValid
        }
    }
}