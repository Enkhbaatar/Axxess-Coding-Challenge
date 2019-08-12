package com.enkhee.codingchallenge.data.repository

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import com.enkhee.codingchallenge.data.db.CommentDao
import com.enkhee.codingchallenge.data.db.entiry.CommentEntry
import java.text.SimpleDateFormat
import java.util.*

class CommentRepositoryImpl(private val commentDao: CommentDao) : CommentRepository {
    @SuppressLint("SimpleDateFormat")
    override suspend fun insertComment(id: String, comment: String) {
        val commentEntry = CommentEntry(
            id,
            comment,
            SimpleDateFormat("MM/dd/yyyy HH:mm:ss")
                .format(Calendar.getInstance().time)
        )
        this.commentDao.insert(commentEntry)
    }

    override suspend fun fetchComments(id: String): LiveData<List<CommentEntry>> {
        return commentDao.getComments(id)
    }
}