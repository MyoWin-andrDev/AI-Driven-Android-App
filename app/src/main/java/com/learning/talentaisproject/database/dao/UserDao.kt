package com.learning.talentaisproject.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.learning.talentaisproject.database.entity.UserEntity

@Dao
interface UserDao {
    
    @Insert
    suspend fun insertUser(user: UserEntity) : Long

    @Query("SELECT user_id FROM tbl_users WHERE username = :username")
    suspend fun getUserIdByName(username : String) : Int

    @Query("SELECT EXISTS(SELECT * FROM tbl_users WHERE username = :username)")
    suspend fun doesUserExist(username: String): Boolean

    @Query("SELECT EXISTS(SELECT * FROM tbl_users WHERE username = :username AND password = :password)")
    suspend fun isPasswordCorrect(username: String, password: String): Boolean
}