package com.enkhee.codingchallenge.ui.gallery

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.enkhee.codingchallenge.R
import com.enkhee.codingchallenge.data.repository.GalleryRepository
import com.enkhee.codingchallenge.ui.adapter.gridview.GalleryAdapter
import com.enkhee.codingchallenge.data.db.entiry.ImageEntry
import com.enkhee.codingchallenge.ui.base.ScopedViewModel
import kotlinx.coroutines.launch

class GalleryViewModel(
    private val galleryRepository: GalleryRepository
) : ScopedViewModel() {
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val searchQuery: MutableLiveData<String> = MutableLiveData()
    val adapter = GalleryAdapter(R.layout.gallery_item, this)
    var images: List<ImageEntry>? = null
    val selectedImage: MutableLiveData<ImageEntry> = MutableLiveData()

    init {
        loadingVisibility.postValue(View.GONE)
        searchQuery.observeForever {
            loadingVisibility.postValue(View.VISIBLE)
            launch {
                galleryRepository.searchGallery(it).observeForever {
                    loadingVisibility.value = View.GONE
                    setImagesInAdapter(it)
                }
            }
        }
    }

    private fun setImagesInAdapter(images: List<ImageEntry>) {
        this.images = images
        this.adapter.notifyDataSetChanged()
    }

    fun onItemClick(position: Int) {
        selectedImage.postValue(images!![position])
    }
}