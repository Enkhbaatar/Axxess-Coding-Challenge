package com.enkhee.codingchallenge.ui.adapter.gridview

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.enkhee.codingchallenge.BR
import com.enkhee.codingchallenge.ui.gallery.GalleryViewModel

class GalleryViewHolder(private var binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(viewModel: GalleryViewModel, position: Int) {
        binding.setVariable(BR.viewModel, viewModel)
        binding.setVariable(BR.position, position)
        binding.executePendingBindings()
    }
}