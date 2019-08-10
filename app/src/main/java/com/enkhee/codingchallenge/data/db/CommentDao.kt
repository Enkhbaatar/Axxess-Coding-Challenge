package com.enkhee.codingchallenge.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.enkhee.codingchallenge.data.db.entiry.CommentEntry

@Dao
interface CommentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(commentEntry: CommentEntry)

    @Query("select * from comment where imageId = :imageId")
    fun getComments(imageId:String):LiveData<List<CommentEntry>>
}