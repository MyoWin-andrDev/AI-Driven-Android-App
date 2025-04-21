package com.learning.talentaisproject.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

// StatusEntity.kt
@Entity(
    tableName = "tbl_status",
    foreignKeys = [ForeignKey(
        entity = UserEntity::class,
        parentColumns = ["user_id"],
        childColumns = ["user_id"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class StatusEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "user_id")
    val user_id : Int ,

    @ColumnInfo(name = "username")
    val username : String,


    @ColumnInfo(name = "content")
    val content: String
)