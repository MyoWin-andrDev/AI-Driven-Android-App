package com.learning.talentaisproject.database.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.learning.talentaisproject.database.dao.StatusDao
import com.learning.talentaisproject.database.dao.UserDao
import com.learning.talentaisproject.database.entity.StatusEntity
import com.learning.talentaisproject.database.entity.UserEntity
import kotlinx.coroutines.InternalCoroutinesApi


@Database(entities = [UserEntity::class,StatusEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun statusDao(): StatusDao
    abstract fun userDao() : UserDao

    companion object{
        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun getDatabaseInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "appDatabase.db").build()
                INSTANCE = instance
                instance
            }

        }
    }
}