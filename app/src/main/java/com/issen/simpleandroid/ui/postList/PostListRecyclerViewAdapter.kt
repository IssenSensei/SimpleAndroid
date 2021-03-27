package com.issen.simpleandroid.ui.postList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.issen.simpleandroid.data.domain.Post
import com.issen.simpleandroid.databinding.ItemPostBinding

class PostListRecyclerViewAdapter :
    ListAdapter<Post, PostListRecyclerViewAdapter.ViewHolder>(PostListDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPostBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    class ViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {
            binding.post = post
            binding.executePendingBindings()
        }
    }
}

object PostListDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem == newItem
    }

}
