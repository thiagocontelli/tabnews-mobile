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
import com.thiagocontelli.tabnewsmobile.data.dtos.GetPostContentDto
import com.thiagocontelli.tabnewsmobile.databinding.FragmentPostBinding
import dagger.hilt.android.AndroidEntryPoint
import io.noties.markwon.Markwon
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@AndroidEntryPoint
class PostFragment : Fragment() {
    private var _binding: FragmentPostBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PostViewModel by viewModels()

    @Inject
    lateinit var markwon: Markwon

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onNavigationClick()

        val user = arguments?.getString("username") ?: ""
        val slug = arguments?.getString("slug") ?: ""

        lifecycleScope.launch {
            showLoading(true)

            viewModel.getPostContent(user, slug).collect { result ->
                result.onSuccess { post -> onSuccess(post) }
                result.onFailure { onFailure() }
            }

            showLoading(false)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun showLoading(showLoading: Boolean) {
        binding.progress.visibility =
            if (showLoading) View.VISIBLE else View.GONE

        binding.scrollView.visibility =
            if (showLoading) View.GONE else View.VISIBLE
    }

    private fun formatDate(date: String): String {
        return LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME)
            .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
    }

    private fun onNavigationClick() {
        binding.postToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun onSuccess(post: GetPostContentDto) {
        val formatted = formatDate(post.published_at)

        binding.tvTitle.text = post.title
        binding.chipUsername.text = post.owner_username
        binding.tvPublishedAt.text = formatted
        binding.tvTabcoins.text = post.tabcoins.toString()

        markwon.setMarkdown(binding.tvBody, post.body)
    }

    private fun onFailure() {
        Toast.makeText(activity, "Houve um erro!", Toast.LENGTH_LONG).show()
    }
}