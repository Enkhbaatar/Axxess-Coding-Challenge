package com.enkhee.codingchallenge.ui.adapter.gridview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.enkhee.codingchallenge.data.db.entiry.ImageEntry
import com.enkhee.codingchallenge.ui.gallery.GalleryViewModel
import org.jetbrains.annotations.NotNull

class GalleryAdapter(@LayoutRes private var layoutID: Int, private var viewModel: GalleryViewModel) :
    RecyclerView.Adapter<GalleryViewHolder>() {
    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.bind(viewModel, position)
    }

    override fun onCreateViewHolder(@NotNull parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false)
        return GalleryViewHolder(binding)
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    override fun getItemCount(): Int {
        return if (viewModel.images.isNullOrEmpty())
            0
        else
            viewModel.images!!.size
    }

    private fun getLayoutIdForPosition(position: Int): Int {
        return layoutID
    }
}