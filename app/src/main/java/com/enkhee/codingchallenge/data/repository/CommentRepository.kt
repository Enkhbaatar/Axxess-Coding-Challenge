package com.enkhee.codingchallenge.data.repository

import androidx.lifecycle.LiveData
import com.enkhee.codingchallenge.data.db.entiry.CommentEntry

interface CommentRepository {
    suspend fun insertComment(id:String, comment:String)
    suspend fun fetchComments(id:String):LiveData<List<CommentEntry>>
}