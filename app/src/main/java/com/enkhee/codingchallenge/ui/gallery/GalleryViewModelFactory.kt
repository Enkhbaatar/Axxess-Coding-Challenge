package com.enkhee.codingchallenge.ui.gallery

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.enkhee.codingchallenge.data.repository.GalleryRepository

class GalleryViewModelFactory(
    private val galleryRepository: GalleryRepository
) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GalleryViewModel(galleryRepository) as T
    }
}