package com.learning.talentaisproject.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.learning.talentaisproject.R
import com.learning.talentaisproject.databinding.ActivityLoginBinding
import com.learning.talentaisproject.myUtil.showToast
import com.learning.talentaisproject.myUtil.validateNotEmpty

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            btnLogin.setOnClickListener {
                showToast("Welcome " + etUsername.text.toString() )
            }
        }
    }
    private fun validateInputs(): Boolean {
        binding.apply {
            val isUsernameValid = usernameTextInputLayout.validateNotEmpty("Username is required")
            val isPasswordValid = passwordTextInputLayout.validateNotEmpty("Password is required")
            return isUsernameValid && isPasswordValid
        }
    }
}