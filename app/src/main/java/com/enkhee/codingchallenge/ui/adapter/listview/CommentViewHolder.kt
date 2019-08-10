package com.enkhee.codingchallenge.ui.adapter.listview

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.enkhee.codingchallenge.BR
import com.enkhee.codingchallenge.ui.image.ImageViewModel

class CommentViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(viewModel: ImageViewModel, position: Int) {
        binding.setVariable(BR.viewModel, viewModel)
        binding.setVariable(BR.position, position)
        binding.executePendingBindings()
    }
}