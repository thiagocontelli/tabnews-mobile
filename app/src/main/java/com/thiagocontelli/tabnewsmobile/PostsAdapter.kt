package com.thiagocontelli.tabnewsmobile

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.thiagocontelli.tabnewsmobile.databinding.PostListItemBinding

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
        binding.postPosition.text = "${position + 1}."
        binding.postTitle.text = posts[position].title
        binding.postTabcoins.text = "${posts[position].tabCoins} tabcoin(s)"
        binding.postAuthor.text = " · ${posts[position].ownerUsername}"
        binding.postComments.text = " · ${posts[position].commentsAmount} comentário(s)"
    }

    override fun getItemCount(): Int = posts.size

    class PostsViewHolder(val binding: PostListItemBinding) : RecyclerView.ViewHolder(binding.root)
}