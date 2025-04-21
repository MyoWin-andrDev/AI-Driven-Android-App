package com.learning.talentaisproject.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

// UserEntity.kt
@Entity(tableName = "tbl_users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    val userId: Int = 0,

    @ColumnInfo(name = "username", collate = ColumnInfo.NOCASE)
    val username: String,

    @ColumnInfo(name = "password")
    val password: String
)