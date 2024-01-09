package com.example.traveljournal

import android.content.Context

object DatabaseManager {
    private var instance: Database? = null

    fun getDatabase(context: Context): Database {
        return instance ?: synchronized(this) {
            instance ?: Database(context).also {
                instance = it
            }
        }
    }
}