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
import com.issen.simpleandroidlibrary.SimpleAndroidAlertDialog

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
            mainActivityViewModel.checkIsOnline(requireContext())
        }

        mainActivityViewModel.isRefreshing.observe(viewLifecycleOwner, Observer {
            binding.swipeRefresh.isRefreshing = it
        })

        val adapter = FeedListRecyclerViewAdapter()
        mainActivityViewModel.feedList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        mainActivityViewModel.hasDataDownloadErrorOccurred.observe(viewLifecycleOwner, Observer {
            if (it) {
                val dialog = SimpleAndroidAlertDialog()
                dialog.showAlert(requireContext(), "Error", "Data download error, please try again")
                mainActivityViewModel.clearDataDownloadError()
                binding.swipeRefresh.isRefreshing = false
            }
        })

        mainActivityViewModel.hasInternetErrorOccurred.observe(viewLifecycleOwner, Observer {
            if (it) {
                val dialog = SimpleAndroidAlertDialog()
                dialog.showAlert(requireContext(), "Error", "No internet connection")
                mainActivityViewModel.clearInternetError()
                binding.swipeRefresh.isRefreshing = false
            }
        })

        binding.feedListRecycler.adapter = adapter
        return binding.root
    }
}