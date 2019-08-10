package com.enkhee.codingchallenge.data.repository

import androidx.lifecycle.LiveData
import com.enkhee.codingchallenge.data.db.CommentDao
import com.enkhee.codingchallenge.data.db.entiry.CommentEntry

class CommentRepositoryImpl(private val commentDao: CommentDao):CommentRepository {
    override suspend fun insertComment(id: String, comment: String) {
        val commentEntry = CommentEntry(imageId = id, comment = comment)
        this.commentDao.insert(commentEntry)
    }

    override suspend fun fetchComments(id: String): LiveData<List<CommentEntry>> {
        return commentDao.getComments(id)
    }
}