package com.learning.talentaisproject.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.learning.talentaisproject.R
import com.learning.talentaisproject.database.database.AppDatabase
import com.learning.talentaisproject.databinding.ActivityLoginBinding
import com.learning.talentaisproject.datastore.UsernameDataStore
import com.learning.talentaisproject.myUtil.showToast
import com.learning.talentaisproject.myUtil.validateNotEmpty
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLoginBinding
    val appDatabase by lazy { AppDatabase.getDatabaseInstance(this) }
    val dataStore by lazy { UsernameDataStore(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            btnLogin.setOnClickListener {
                if(validateInputs()){
                    lifecycleScope.launch {
                        if(appDatabase.userDao().doesUserExist(etUsername.text.toString())&& appDatabase.userDao().isPasswordCorrect(etUsername.text.toString(), etPassword.text.toString())){
                            showToast("Login Successful")
                            dataStore.saveUsername(etUsername.text.toString())
                            Intent(this@LoginActivity, HomeActivity::class.java).also {
                                startActivity(it)
                            }
                        }
                        else{
                            showToast("Login Failed")
                        }
                    }
                }
            }
            btRegister.setOnClickListener {
                Intent(this@LoginActivity, RegisterActivity::class.java).also {
                    startActivity(it)
                }
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