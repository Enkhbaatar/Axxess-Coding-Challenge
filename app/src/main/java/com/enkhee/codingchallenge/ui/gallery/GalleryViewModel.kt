package com.enkhee.codingchallenge.ui.gallery

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.enkhee.codingchallenge.R
import com.enkhee.codingchallenge.data.repository.GalleryRepository
import com.enkhee.codingchallenge.ui.adapter.gridview.GalleryAdapter
import com.enkhee.codingchallenge.data.db.entiry.ImageEntry
import com.enkhee.codingchallenge.ui.base.BaseViewModel

class GalleryViewModel(
    private val galleryRepository: GalleryRepository
) : BaseViewModel() {

    val searchQuery: MutableLiveData<String> = MutableLiveData()
    val adapter = GalleryAdapter(R.layout.gallery_item, this)
    var images: List<ImageEntry>? = null
    val selectedImage: MutableLiveData<ImageEntry> = MutableLiveData()
    val eventState = galleryRepository.eventState

    lateinit var selectedView: View

    init {
        loadingVisibility.postValue(View.GONE)

        searchQuery.observeForever {
            if (it.isNotEmpty()) {
                galleryRepository.searchGallery(it).observeForever {
                    if (it.isEmpty())
                        message.postValue("No Images")
                    else
                        message.postValue("")
                    setImagesInAdapter(it)
                }
            }
        }
    }

    private fun setImagesInAdapter(images: List<ImageEntry>) {
        this.images = images
        this.adapter.notifyDataSetChanged()
    }

    fun onItemClick(view: View, position: Int) {
        selectedView = view
        selectedImage.postValue(images!![position])
    }
}