package com.aengussong.instacopy.model

import com.google.gson.annotations.SerializedName

data class Post(
    val username: String,
    val userpic: String,
    val location: String?,
    @SerializedName("post_images")
    val postImages: List<String>,
    val likes: LikeData,
    val description: String,
    @SerializedName("post_time")
    val postTime: Long
)