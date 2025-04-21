package com.learning.talentaisproject.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.learning.talentaisproject.database.database.AppDatabase
import com.learning.talentaisproject.database.entity.UserEntity
import com.learning.talentaisproject.databinding.ActivityRegisterBinding
import com.learning.talentaisproject.myUtil.showToast
import com.learning.talentaisproject.myUtil.validateConfirmPassword
import com.learning.talentaisproject.myUtil.validateNotEmpty
import com.learning.talentaisproject.myUtil.validatePassword
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRegisterBinding
    val appDatabase by lazy {AppDatabase.getDatabaseInstance(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            btnSubmit.setOnClickListener {
                if (validateInputs()) {
                    // All inputs are valid, proceed with registration
                   lifecycleScope.launch {
                       if((appDatabase.userDao().insertUser(UserEntity( 0 ,etUsername.text.toString(), etPassword.text.toString()))) == 1L){
                           showToast("Account Created Successfully")
                           Intent(this@RegisterActivity, LoginActivity::class.java).also {
                               startActivity(it)
                           }
                       }
                       else{
                           showToast("Account Creating Failed.")
                       }
                   }
                }
                else{
                    showToast("Username and Password must be filled.")
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