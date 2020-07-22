package com.aengussong.instacopy.adapter.viewholder

import android.view.LayoutInflater
import android.view.View
import com.aengussong.instacopy.R
import com.aengussong.instacopy.adapter.ViewPagerAdapter
import com.aengussong.instacopy.model.Post
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.item_post.view.*
import kotlinx.android.synthetic.main.layout_viewpager.view.*

/** ViewHolder for post with several images. Images are displayed in ViewPager2*/
class ImageSetPostHolder(itemView: View) : BasePostHolder(itemView) {

    private val pagerAdapter = ViewPagerAdapter()

    init {
        itemView.apply {
            val viewPager = LayoutInflater.from(image_container.context)
                .inflate(R.layout.layout_viewpager, image_container, false)
            image_container.addView(viewPager)
            tabs.visibility = View.VISIBLE

            post_viewpager.adapter = pagerAdapter
            TabLayoutMediator(tabs, post_viewpager) { tab, _ ->
                tab.view.isClickable = false
            }.attach()
        }
    }

    override fun bind(post: Post) {
        super.bind(post)
        pagerAdapter.updateData(post.postImages)
    }
}