package com.issen.simpleandroid.ui.postList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.issen.simpleandroid.R

class PostListFragment : Fragment() {

    private lateinit var postListViewModel: PostListViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        postListViewModel =
                ViewModelProvider(this).get(PostListViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_post_list, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        postListViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}