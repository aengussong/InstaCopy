package com.aengussong.instacopy.adapter.viewholder

import android.view.View
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.aengussong.instacopy.R
import com.aengussong.instacopy.model.Post
import com.aengussong.instacopy.utils.DescriptionFormatter
import com.aengussong.instacopy.utils.HoursCalculator
import com.aengussong.instacopy.utils.StringCombiner
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_post.view.*

/**Binds common views between different view holders*/
open class BasePostHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    open fun bind(post: Post) {
        itemView.apply {
            Glide.with(this).load(post.userpic).into(userpic)
            username.text = post.username
            place.visibility = if (post.location != null) View.VISIBLE else View.GONE
            post.location?.let {
                place.text = resources.getString(R.string.location_template, it)
            }

            post.likes.let { likeData ->
                val likeResource =
                    if (likeData.isLiked) R.drawable.ic_like_filled else R.drawable.ic_like_unfilled
                btn_like.setImageResource(likeResource)
                val likeText = if (likeData.likedBy.isEmpty()) {
                    resources.getQuantityString(
                        R.plurals.likes_count,
                        likeData.likesCount,
                        likeData.likesCount
                    )
                } else {
                    val likedByCombined =
                        StringCombiner(", ").combine(likeData.likedBy).toString()
                    val likedByCount = resources.getQuantityString(
                        R.plurals.liked_by,
                        likeData.likedBy.size,
                        likeData.likedBy.size
                    )
                    resources.getString(R.string.liked_by_text, likedByCombined, likedByCount)
                }
                post_likes.text =
                    HtmlCompat.fromHtml(likeText, HtmlCompat.FROM_HTML_MODE_LEGACY)

                post_description.text =
                    DescriptionFormatter.format(post.username, post.description)
            }

            val elapsedHours = HoursCalculator.calculateElapsedHours(post.postTime)
            post_time.text =
                resources.getQuantityString(R.plurals.hours_elapsed, elapsedHours, elapsedHours)
        }
    }
}