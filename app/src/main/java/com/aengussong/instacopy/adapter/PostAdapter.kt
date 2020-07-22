package com.aengussong.instacopy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.aengussong.instacopy.R
import com.aengussong.instacopy.model.Post
import com.aengussong.instacopy.utils.DescriptionFormatter
import com.aengussong.instacopy.utils.HoursCalculator
import com.aengussong.instacopy.utils.StringCombiner
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.item_post.view.*
import kotlinx.android.synthetic.main.layout_image.view.*
import kotlinx.android.synthetic.main.layout_viewpager.view.*

private const val IMAGE_SINGLE = 0
private const val IMAGE_SET = 1

class PostAdapter(private val posts: Array<Post>) :
    RecyclerView.Adapter<PostAdapter.BasePostHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasePostHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return when (viewType) {
            IMAGE_SINGLE -> {
                val imageView = LayoutInflater.from(view.image_container.context)
                    .inflate(R.layout.layout_image, view.image_container, false)
                view.image_container.addView(imageView)
                ImageSinglePostHolder(view)
            }
            IMAGE_SET -> {
                val viewPager = LayoutInflater.from(view.image_container.context)
                    .inflate(R.layout.layout_viewpager, view.image_container, false)
                view.image_container.addView(viewPager)
                view.tabs.visibility = View.VISIBLE
                ImageSetPostHolder(view)
            }
            else -> throw java.lang.IllegalArgumentException("Unknown view type: $viewType")
        }
    }

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: BasePostHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemViewType(position: Int): Int {
        return when (posts[position].postImages.size) {
            0 -> throw IllegalArgumentException("Post should contains at least one image")
            1 -> IMAGE_SINGLE
            else -> IMAGE_SET
        }
    }

    class ImageSinglePostHolder(itemView: View) : BasePostHolder(itemView) {
        override fun bind(post: Post) {
            super.bind(post)
            Glide.with(itemView).load(post.postImages.first()).into(itemView.post_image)
        }
    }

    class ImageSetPostHolder(itemView: View) : BasePostHolder(itemView) {

        private val pagerAdapter = ViewPagerAdapter()

        init {
            itemView.post_viewpager.adapter = pagerAdapter
            TabLayoutMediator(itemView.tabs, itemView.post_viewpager) { tab, _ ->
                tab.view.isClickable = false
            }.attach()
        }

        override fun bind(post: Post) {
            super.bind(post)
            pagerAdapter.updateData(post.postImages)
        }
    }

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
}