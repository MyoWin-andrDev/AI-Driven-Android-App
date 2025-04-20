package com.learning.talentaisproject.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.learning.talentaisproject.database.entity.StatusEntity


@Dao
interface StatusDao {
    @Insert
    suspend fun insert(status: StatusEntity)

    @Update
    suspend fun update(status: StatusEntity)

    @Delete
    suspend fun delete(status: StatusEntity)

    @Query("SELECT * FROM tbl_status")
    fun getAllStatuses(): List<StatusEntity>
}