package com.thiagocontelli.tabnewsmobile.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thiagocontelli.tabnewsmobile.databinding.FragmentRecentsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecentsFragment : Fragment() {
    private var _binding: FragmentRecentsBinding? = null
    private val binding get() = _binding!!
    private lateinit var postsAdapter: PostsAdapter
    private val viewModel: RecentsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postsAdapter = PostsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecentsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preparePostsRecyclerView()
        getPosts()
        onReachLastElement()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun preparePostsRecyclerView() {
        binding.postsRecyclerView.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            adapter = postsAdapter
        }
    }

    private fun getPosts() {
        lifecycleScope.launch {
            viewModel.getPosts().collect { result ->
                result.onSuccess { posts ->
                    postsAdapter.setPosts(posts)
                }
                result.onFailure {
                    Toast.makeText(activity, "Houve um erro!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun onReachLastElement() {
        binding.postsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val linearLayoutManager = recyclerView.layoutManager as LinearLayoutManager?
                if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == linearLayoutManager.itemCount - 1) {
                    getPosts()
                }
                super.onScrolled(recyclerView, dx, dy)
            }
        })
    }
}