package com.enkhee.codingchallenge.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.enkhee.codingchallenge.data.db.entiry.CommentEntry

@Database(
    entities = [CommentEntry::class], version = 1
)
abstract class CommentDatabase : RoomDatabase() {
    abstract fun commentDao(): CommentDao

    companion object {
        @Volatile
        private var instance: CommentDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            CommentDatabase::class.java, "comment"
        ).build()
    }
}