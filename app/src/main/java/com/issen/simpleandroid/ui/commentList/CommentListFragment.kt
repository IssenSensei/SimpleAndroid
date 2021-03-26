package com.issen.simpleandroid.ui.commentList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.issen.simpleandroid.R

class CommentListFragment : Fragment() {

    private lateinit var commentListViewModel: CommentListViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        commentListViewModel =
                ViewModelProvider(this).get(CommentListViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_comment_list, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        commentListViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}