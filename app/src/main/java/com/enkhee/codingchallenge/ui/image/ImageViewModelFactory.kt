package com.enkhee.codingchallenge.ui.image

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.enkhee.codingchallenge.data.repository.CommentRepository

class ImageViewModelFactory(private val commentRepository: CommentRepository):
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ImageViewModel(commentRepository) as T
    }
}