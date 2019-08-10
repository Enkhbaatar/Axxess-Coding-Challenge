package com.enkhee.codingchallenge.ui.adapter.listview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.enkhee.codingchallenge.ui.image.ImageViewModel

class CommentAdapter(@LayoutRes private var layoutID: Int, private var viewModel: ImageViewModel) :
    RecyclerView.Adapter<CommentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false)
        return CommentViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (viewModel.comments.isNullOrEmpty())
            0
        else
            viewModel.comments!!.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(viewModel, position)
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    private fun getLayoutIdForPosition(position: Int): Int {
        return layoutID
    }
}