package com.aengussong.instacopy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aengussong.instacopy.R
import com.aengussong.instacopy.adapter.viewholder.BasePostHolder
import com.aengussong.instacopy.adapter.viewholder.ImageSetPostHolder
import com.aengussong.instacopy.adapter.viewholder.ImageSinglePostHolder
import com.aengussong.instacopy.const.*
import com.aengussong.instacopy.model.Post
import com.aengussong.instacopy.utils.doAsync
import kotlinx.android.synthetic.main.item_post.view.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel

private const val IMAGE_SINGLE = 0
private const val IMAGE_SET = 1

class PostAdapter(private val posts: Array<Post>) :
    RecyclerView.Adapter<BasePostHolder>() {

    private val channel = Channel<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasePostHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        addClickListeners(view)
        return when (viewType) {
            IMAGE_SINGLE -> ImageSinglePostHolder(view)
            IMAGE_SET -> ImageSetPostHolder(view)
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

    fun getOnClickChannel(): ReceiveChannel<String> = channel

    private fun addClickListeners(view: View) {
        view.apply {
            userpic.sendOnClick(N_EASTER_EGG)
            btn_options.sendOnClick(N_OPTIONS)
            btn_like.sendOnClick(N_LIKE)
            btn_comment.sendOnClick(N_COMMENT)
            btn_message.sendOnClick(N_SHARE)
            btn_bookmark.sendOnClick(N_BOOKMARK)
        }
    }

    private fun View.sendOnClick(msg: String) {
        setOnClickListener { doAsync { channel.send(msg) } }
    }
}