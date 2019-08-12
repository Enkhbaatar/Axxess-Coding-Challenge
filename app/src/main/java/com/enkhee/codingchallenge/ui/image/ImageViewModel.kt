package com.enkhee.codingchallenge.ui.image

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.enkhee.codingchallenge.R
import com.enkhee.codingchallenge.data.db.entiry.CommentEntry
import com.enkhee.codingchallenge.data.db.entiry.ImageEntry
import com.enkhee.codingchallenge.data.repository.CommentRepository
import com.enkhee.codingchallenge.ui.adapter.listview.CommentAdapter
import com.enkhee.codingchallenge.ui.base.ScopedViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ImageViewModel(private val commentRepository: CommentRepository) :
    ScopedViewModel() {
    lateinit var image: ImageEntry
    val adapter = CommentAdapter(R.layout.comment_item, this)
    var comments: List<CommentEntry>? = null
    val comment: MutableLiveData<String> = MutableLiveData()

    init {
        fetchComments()
        _message.postValue("No Comments")
    }

    private fun setImagesInAdapter(comments: List<CommentEntry>) {
        this.comments = comments
        this.adapter.notifyDataSetChanged()
    }

    fun onClickSubmit() {
        launch {
            loadingVisibility.postValue(View.VISIBLE)
            withContext(Dispatchers.IO) {
                if (comment.value != null) {
                    commentRepository.insertComment(image.id, comment.value!!)
                    fetchComments()
                }
                comment.postValue("")
            }
        }
    }

    private fun fetchComments() {
        if (loadingVisibility.value == View.GONE)
            loadingVisibility.postValue(View.VISIBLE)

        launch {
            commentRepository.fetchComments(image.id).observeForever {
                if (it.size > 0)
                    _message.postValue("")
                setImagesInAdapter(it)
                loadingVisibility.postValue(View.GONE)
            }
        }
    }
}