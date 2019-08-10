package com.enkhee.codingchallenge.data.db.entiry


import com.google.gson.annotations.SerializedName

data class GalleryEntry(
    @SerializedName("id")
    val id: String,
    @SerializedName("images")
    val images: List<ImageEntry>,
    @SerializedName("images_count")
    val imagesCount: Int,
    @SerializedName("is_album")
    val isAlbum: Boolean,
    @SerializedName("link")
    val link: String,
    @SerializedName("title")
    val title: String
)