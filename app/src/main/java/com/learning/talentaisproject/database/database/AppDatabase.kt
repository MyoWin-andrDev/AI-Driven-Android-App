package com.learning.talentaisproject.database.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.learning.talentaisproject.database.dao.StatusDao
import com.learning.talentaisproject.database.entity.StatusEntity

@Database(entities = [StatusEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun statusDao(): StatusDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "status_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}