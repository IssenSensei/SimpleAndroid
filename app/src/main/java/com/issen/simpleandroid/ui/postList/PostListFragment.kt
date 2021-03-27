package com.issen.simpleandroid.ui.postList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.issen.simpleandroid.SimpleAndroidApplication
import com.issen.simpleandroid.databinding.FragmentPostListBinding
import com.issen.simpleandroid.ui.MainActivityViewModel
import com.issen.simpleandroid.ui.MainActivityViewModelFactory

class PostListFragment : Fragment() {

    private val mainActivityViewModel: MainActivityViewModel by activityViewModels {
        MainActivityViewModelFactory(
            (requireActivity().application as SimpleAndroidApplication).repository
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentPostListBinding.inflate(inflater, container, false)

        val adapter = PostListRecyclerViewAdapter()
        mainActivityViewModel.postList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        binding.postList.adapter = adapter

        binding.viewModel = mainActivityViewModel
        binding.lifecycleOwner = this
        return binding.root
    }
}