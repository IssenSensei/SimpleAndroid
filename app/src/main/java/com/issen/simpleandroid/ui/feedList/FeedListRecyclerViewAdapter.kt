package com.issen.simpleandroid.ui.feedList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.issen.simpleandroid.data.domain.Comment
import com.issen.simpleandroid.data.domain.Feed
import com.issen.simpleandroid.data.domain.Photo
import com.issen.simpleandroid.databinding.ItemCommentBinding
import com.issen.simpleandroid.databinding.ItemPhotoBinding

private const val ITEM_VIEW_TYPE_COMMENT = 0
private const val ITEM_VIEW_TYPE_PHOTO = 1

class FeedListRecyclerViewAdapter :
    ListAdapter<Feed, RecyclerView.ViewHolder>(FeedListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_COMMENT -> CommentViewHolder.from(parent)
            ITEM_VIEW_TYPE_PHOTO -> PhotoViewHolder.from(parent)
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CommentViewHolder -> holder.bind(getItem(position).item.second as Comment)
            is PhotoViewHolder -> holder.bind(getItem(position).item.second as Photo)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)!!.item.first) {
            ITEM_VIEW_TYPE_COMMENT -> ITEM_VIEW_TYPE_COMMENT
            ITEM_VIEW_TYPE_PHOTO -> ITEM_VIEW_TYPE_PHOTO
            else -> -1
        }
    }

    class CommentViewHolder(private val binding: ItemCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Comment) {
            binding.name = item.name
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): CommentViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemCommentBinding.inflate(layoutInflater, parent, false)
                return CommentViewHolder(binding)
            }
        }
    }

    class PhotoViewHolder(private val binding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Photo) {
            binding.thumbnailUrl = item.thumbnailUrl
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): PhotoViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemPhotoBinding.inflate(layoutInflater, parent, false)
                return PhotoViewHolder(binding)
            }
        }
    }
}

class FeedListDiffCallback : DiffUtil.ItemCallback<Feed>() {
    override fun areItemsTheSame(oldItem: Feed, newItem: Feed): Boolean {
        return if (oldItem.item.second is Comment && newItem.item.second is Comment) {
            (oldItem.item.second as Comment).id == (newItem.item.second as Comment).id
        } else if (oldItem.item.second is Photo && newItem.item.second is Photo) {
            (oldItem.item.second as Photo).id == (newItem.item.second as Photo).id
        } else {
            false
        }
    }

    override fun areContentsTheSame(oldItem: Feed, newItem: Feed): Boolean {
        return if (oldItem.item.second is Comment && newItem.item.second is Comment) {
            oldItem.item.second as Comment == newItem.item.second as Comment
        } else if (oldItem.item.second is Photo && newItem.item.second is Photo) {
            oldItem.item.second as Photo == newItem.item.second as Photo
        } else {
            false
        }
    }
}
