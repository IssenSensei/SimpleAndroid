package com.issen.simpleandroid.ui.feedList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.issen.simpleandroid.SimpleAndroidApplication
import com.issen.simpleandroid.databinding.FragmentFeedListBinding
import com.issen.simpleandroid.ui.MainActivityViewModel
import com.issen.simpleandroid.ui.MainActivityViewModelFactory

class FeedListFragment : Fragment() {

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
        val binding = FragmentFeedListBinding.inflate(inflater, container, false)

        binding.swipeRefresh.setOnRefreshListener {
            mainActivityViewModel.refresh()
        }

        mainActivityViewModel.isRefreshing.observe(viewLifecycleOwner, Observer {
            binding.swipeRefresh.isRefreshing = it
        })

        val adapter = FeedListRecyclerViewAdapter()
        mainActivityViewModel.feedList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
        binding.feedListRecycler.adapter = adapter

        return binding.root
    }
}