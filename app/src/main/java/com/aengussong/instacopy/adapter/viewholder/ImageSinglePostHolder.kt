package com.aengussong.instacopy.adapter.viewholder

import android.view.LayoutInflater
import android.view.View
import com.aengussong.instacopy.R
import com.aengussong.instacopy.model.Post
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_post.view.*
import kotlinx.android.synthetic.main.layout_image.view.*

/** ViewHolder for post with single image. Image loaded into view vie Glide*/
class ImageSinglePostHolder(itemView: View) : BasePostHolder(itemView) {

    init {
        val imageView = LayoutInflater.from(itemView.image_container.context)
            .inflate(R.layout.layout_image, itemView.image_container, false)
        itemView.image_container.addView(imageView)
    }

    override fun bind(post: Post) {
        super.bind(post)
        Glide.with(itemView).load(post.postImages.first()).into(itemView.post_image)
    }
}