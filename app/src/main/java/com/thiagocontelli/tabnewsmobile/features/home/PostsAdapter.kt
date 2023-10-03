package com.thiagocontelli.tabnewsmobile.features.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thiagocontelli.tabnewsmobile.databinding.PostListItemBinding
import com.thiagocontelli.tabnewsmobile.models.Post

class PostsAdapter : RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {
    private var posts: List<Post> = emptyList()

    fun setPosts(posts: List<Post>) {
        this.posts += posts
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): PostsViewHolder {
        return PostsViewHolder(
            PostListItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val binding = holder.binding
        val post = posts[position]
        binding.position.text = "${position + 1}."
        binding.postTitle.text = post.title
        binding.tabcoins.text =
            "${post.tabcoins} ${if (post.tabcoins > 1) "tabcoins" else "tabcoin"}"
        binding.username.text = " · ${post.username}"
        binding.comments.text =
            " · ${post.commentsAmount} ${if (post.commentsAmount > 1) "comentários" else "comentário"}"
    }

    override fun getItemCount(): Int = posts.size

    class PostsViewHolder(val binding: PostListItemBinding) : RecyclerView.ViewHolder(binding.root)
}