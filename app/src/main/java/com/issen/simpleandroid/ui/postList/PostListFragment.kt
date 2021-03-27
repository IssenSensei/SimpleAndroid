package com.issen.simpleandroid.ui.postList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.issen.simpleandroid.databinding.FragmentPostListBinding
import com.issen.simpleandroid.ui.MainActivityViewModel

class PostListFragment : Fragment() {

    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPostListBinding.inflate(inflater, container, false)

        binding.viewModel = mainActivityViewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}