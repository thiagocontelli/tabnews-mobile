package com.thiagocontelli.tabnewsmobile.features.post

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.thiagocontelli.tabnewsmobile.databinding.FragmentPostBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class PostFragment : Fragment() {
    private var _binding: FragmentPostBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PostViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val user = arguments?.getString("username") ?: ""
        val slug = arguments?.getString("slug") ?: ""
        lifecycleScope.launch {
            viewModel.getPostContent(user, slug).collect { result ->
                result.onSuccess { post ->
                    val formatted =
                        LocalDateTime.parse(post.published_at, DateTimeFormatter.ISO_DATE_TIME)
                            .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))

                    binding.tvTitle.text = post.title
                    binding.chipUsername.text = post.owner_username
                    binding.tvPublishedAt.text = formatted
                    binding.tvBody.text = post.body
                    binding.tvTabcoins.text = post.tabcoins.toString()
                }
                result.onFailure {
                    Toast.makeText(activity, "Houve um erro!", Toast.LENGTH_LONG).show()
                }
            }
        }
        binding.postToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}