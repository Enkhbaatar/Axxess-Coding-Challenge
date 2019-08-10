package com.enkhee.codingchallenge.data.network.response


import com.enkhee.codingchallenge.data.db.entiry.GalleryEntry
import com.google.gson.annotations.SerializedName

data class GallerySearchResponse(
    @SerializedName("data")
    val `data`: List<GalleryEntry>,
    @SerializedName("status")
    val status: Int,
    @SerializedName("success")
    val success: Boolean
)