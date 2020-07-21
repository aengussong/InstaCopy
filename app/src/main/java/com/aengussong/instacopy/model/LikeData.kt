package com.aengussong.instacopy.model

import com.google.gson.annotations.SerializedName

data class LikeData(
    val isLiked: Boolean,
    @SerializedName("liked_by")
    val likedBy: List<String>,
    @SerializedName("likes_count")
    val likesCount: Int
)