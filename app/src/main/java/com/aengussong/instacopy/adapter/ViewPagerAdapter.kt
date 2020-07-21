package com.aengussong.instacopy.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aengussong.instacopy.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_post_image.view.*

class ViewPagerAdapter : RecyclerView.Adapter<ViewPagerAdapter.PagerVH>() {

    private val data = mutableListOf<String>()

    class PagerVH(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerVH {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_post_image, parent, false)
        return PagerVH(view)
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: PagerVH, position: Int) {
        Glide.with(holder.itemView).load(data[position]).into(holder.itemView.vp_image)
    }

    fun updateData(newData: List<String>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }
}