package com.enkhee.codingchallenge.ui.gallery

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.enkhee.codingchallenge.R
import com.enkhee.codingchallenge.data.repository.GalleryRepository
import com.enkhee.codingchallenge.ui.adapter.gridview.GalleryAdapter
import com.enkhee.codingchallenge.data.db.entiry.ImageEntry
import com.enkhee.codingchallenge.ui.base.BaseViewModel

class GalleryViewModel(
    private val galleryRepository: GalleryRepository
) : BaseViewModel() {
    private lateinit var classBack:GalleryActivityCallBack
    val searchQuery: MutableLiveData<String> = MutableLiveData()
    val adapter = GalleryAdapter(R.layout.gallery_item, this)
    var images: List<ImageEntry>? = null
    val eventState = galleryRepository.eventState

    init {
        loadingVisibility.postValue(View.GONE)
        _message.postValue("No Images")

        searchQuery.observeForever {
            if (it.isNotEmpty()) {
                sendRequest(it)
            }
        }
        sendRequest("")
    }

    private fun sendRequest(query:String){
        galleryRepository.searchGallery(query).observeForever {
            setImagesInAdapter(it)
        }
    }

    private fun setImagesInAdapter(images: List<ImageEntry>) {
        if (images.isEmpty())
            _message.postValue("No images")
        else
            _message.postValue("")

        this.images = images
        this.adapter.notifyDataSetChanged()
    }

    fun setCallBack(classBack: GalleryActivityCallBack){
        this.classBack = classBack
    }

    fun onItemClick(view: View, position: Int) {
        this.classBack.onItemClick(view, images!![position])
    }
}