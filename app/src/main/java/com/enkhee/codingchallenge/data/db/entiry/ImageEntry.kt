package com.enkhee.codingchallenge.data.db.entiry


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ImageEntry(
    @SerializedName("description")
    val description: Any,
    @SerializedName("height")
    val height: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("link")
    val link: String,
    @SerializedName("size")
    val size: Int,
    @SerializedName("width")
    val width: Int
) : Serializable