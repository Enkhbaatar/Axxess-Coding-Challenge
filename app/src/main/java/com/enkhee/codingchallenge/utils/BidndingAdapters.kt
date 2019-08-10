package com.enkhee.codingchallenge.utils

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.enkhee.codingchallenge.utils.extention.getParentActivity
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.enkhee.codingchallenge.R
import com.enkhee.codingchallenge.ui.adapter.gridview.SpacesItemDecoration

@BindingAdapter("gridAdapter")
fun bindingRecyclerViewAdapter(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<*> ){
    //recyclerView.setHasFixedSize(true)
    recyclerView.layoutManager = GridLayoutManager(recyclerView.context, 4)
    recyclerView.addItemDecoration(
        SpacesItemDecoration(
            recyclerView.context,
            R.dimen.item_offset
        )
    )
    recyclerView.adapter = adapter
}

@BindingAdapter("listAdapter")
fun bindingRecyclerListViewAdapter(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<*> ){
    //recyclerView.setHasFixedSize(true)
    recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
    recyclerView.addItemDecoration(
        SpacesItemDecoration(
            recyclerView.context,
            R.dimen.item_offset
        )
    )
    recyclerView.adapter = adapter
}

@BindingAdapter("imageUrl")
fun bindRecyclerViewAdapter(imageView: ImageView, imageUrl: String?) {
    if (imageUrl != null) {
        if (imageView.getTag(R.id.image_url) == null || !imageView.getTag(R.id.image_url).equals(imageUrl)) {
            imageView.setImageBitmap(null)
            imageView.setTag(R.id.image_url, imageUrl)
            Glide.with(imageView).load(imageUrl).into(imageView)
        }
    } else {
        imageView.setTag(R.id.image_url, null)
        imageView.setImageBitmap(null)
    }
}

@BindingAdapter("mutableVisibility")
fun setMutableVisibility(view: View, visibility: MutableLiveData<Int>?) {
    val parentActivity: AppCompatActivity? = view.getParentActivity()
    if(parentActivity != null && visibility != null) {
        visibility.observe(parentActivity, Observer { value -> view.visibility = value?: View.VISIBLE})
    }
}
