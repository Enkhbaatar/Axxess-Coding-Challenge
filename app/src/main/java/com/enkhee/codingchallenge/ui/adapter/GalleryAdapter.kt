package com.enkhee.codingchallenge.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.enkhee.codingchallenge.ui.gallery.GalleryViewModel
import org.jetbrains.annotations.NotNull


class GalleryAdapter(@LayoutRes private var layoutID: Int, private var viewModel: GalleryViewModel) :
    RecyclerView.Adapter<GalleryViewHolder>() {

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.

    }

    override fun onCreateViewHolder(@NotNull parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val biding:ViewDataBinding = DataBindingUtil.inflate(layoutInflater, viewType, parent, false)
        return GalleryViewHolder(biding)
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}