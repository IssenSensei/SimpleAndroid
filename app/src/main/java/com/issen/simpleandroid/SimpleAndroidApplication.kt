package com.issen.simpleandroid

import android.app.Application
import com.issen.simpleandroid.data.Repository
import com.issen.simpleandroid.data.local.SimpleAndroidRoomDatabase

class SimpleAndroidApplication : Application() {

    private val database by lazy { SimpleAndroidRoomDatabase.getDatabase(this) }
    val repository by lazy {
        Repository(
            database.localPostDao()
        )
    }
}