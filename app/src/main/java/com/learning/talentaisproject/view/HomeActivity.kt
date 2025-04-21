package com.learning.talentaisproject.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.learning.talentaisproject.R
import com.learning.talentaisproject.adapter.StatusAdapter
import com.learning.talentaisproject.database.database.AppDatabase
import com.learning.talentaisproject.database.entity.StatusEntity
import com.learning.talentaisproject.databinding.ActivityHomeBinding
import com.learning.talentaisproject.datastore.UsernameDataStore
import com.learning.talentaisproject.myUtil.showEditDialog
import com.learning.talentaisproject.myUtil.showToast
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val appDatabase by lazy { AppDatabase.getDatabaseInstance(this) }
    private val usernameDataStore by lazy { UsernameDataStore(this) }
    private lateinit var username: String
    private lateinit var statusAdapter: StatusAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        statusAdapter = StatusAdapter(
            onEditClick = { editStatus(it) },
            onDeleteClick = { deleteStatus(it) }
        )

        binding.apply {
            rvStatusList.adapter = statusAdapter

            lifecycleScope.launch {
                // Get username from DataStore once
                username = usernameDataStore.usernameFlow.first()!!
                loadStatuses()
            }

            btnUpload.setOnClickListener {
                lifecycleScope.launch {
                    val statusText = etStatus.text.toString().trim()
                    if (statusText.isNotEmpty()) {
                        val inserted = appDatabase.statusDao().insertStatus(StatusEntity(  0 , user_id = appDatabase.userDao().getUserIdByName(username), username = username, content =  statusText))
                        showToast("Status Uploaded Successfully")
                        etStatus.text = null
                        loadStatuses()
                    } else {
                        showToast("Status Cannot be Empty!")
                    }
                }
            }

            tbToolbar.setOnMenuItemClickListener {
                if (it.itemId == R.id.logout) {
                    lifecycleScope.launch {
                        usernameDataStore.clearUsername()
                        startActivity(Intent(this@HomeActivity, LoginActivity::class.java))
                        showToast("Logout Successful")
                        finish()
                    }
                    true
                } else false
            }
        }
    }

    private suspend fun loadStatuses() {
        val statuses = appDatabase.statusDao().getStatusesByUsername(username)
        statusAdapter.submitList(statuses)
    }

    private fun editStatus(status: StatusEntity) {
        showEditDialog(status.content) { newContent ->
            lifecycleScope.launch {
                val updatedStatus = status.copy(content = newContent)
                if((appDatabase.statusDao().updateStatus(updatedStatus)) == 1){
                    loadStatuses()
                    showToast("Status updated!")
                }
            }
        }
    }

    private fun deleteStatus(status: StatusEntity) {
        lifecycleScope.launch {
            showToast(if (appDatabase.statusDao().deleteStatus(status) == 1) "Status Deleted Successfully" else "Something Went Wrong!")
            loadStatuses()
        }
    }
}
