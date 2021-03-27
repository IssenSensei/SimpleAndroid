package com.issen.simpleandroid.ui.commentList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.issen.simpleandroid.R
import com.issen.simpleandroid.ui.MainActivityViewModel

class CommentListFragment : Fragment() {

    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
//        commentListViewModel =
//                ViewModelProvider(this).get(CommentListViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_comment_list, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
//        commentListViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return root
    }
}