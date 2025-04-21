package com.learning.talentaisproject.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.learning.talentaisproject.database.entity.StatusEntity
import com.learning.talentaisproject.database.entity.UserEntity


// StatusDao.kt
@Dao
interface StatusDao {
    // User operations
    @Insert
    suspend fun insertUser(user: UserEntity): Long

    @Query("SELECT * FROM tbl_users WHERE username = :username AND password = :password LIMIT 1")
    suspend fun getUser(username: String, password: String): UserEntity?

    // Status operations
    @Insert
    suspend fun insertStatus(status: StatusEntity): Long

    @Update
    suspend fun updateStatus(status: StatusEntity) : Int

    @Delete
    suspend fun deleteStatus(status: StatusEntity) : Int

    @Transaction
    @Query("SELECT * FROM tbl_status WHERE user_id = :userId")
    suspend fun getStatusesForUser(userId: Int): List<StatusEntity>

    @Transaction
    @Query("SELECT s.* FROM tbl_status s INNER JOIN tbl_users u ON s.user_id = u.user_id WHERE u.username = :username")
    suspend fun getStatusesByUsername(username: String): List<StatusEntity>
}