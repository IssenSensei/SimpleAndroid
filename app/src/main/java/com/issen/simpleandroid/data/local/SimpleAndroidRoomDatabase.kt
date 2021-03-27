package com.issen.simpleandroid.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LocalPost::class], version = 1, exportSchema = false)
public abstract class SimpleAndroidRoomDatabase : RoomDatabase() {

    abstract fun localPostDao(): LocalPostDao

    companion object {
        @Volatile
        private var INSTANCE: SimpleAndroidRoomDatabase? = null

        fun getDatabase(context: Context): SimpleAndroidRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SimpleAndroidRoomDatabase::class.java,
                    "simple_android_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}