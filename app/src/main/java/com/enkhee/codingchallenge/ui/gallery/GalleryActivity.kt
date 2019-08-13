package com.enkhee.codingchallenge.ui.gallery

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.enkhee.codingchallenge.R
import com.enkhee.codingchallenge.data.db.entiry.ImageEntry
import com.enkhee.codingchallenge.databinding.ActivityGalleryBinding
import com.enkhee.codingchallenge.ui.image.ImageActivity
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_gallery.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


class GalleryActivity : AppCompatActivity(), KodeinAware, GalleryActivityCallBack {
    private lateinit var binding: ActivityGalleryBinding
    override val kodein by kodein()
    private val viewModelFactory: GalleryViewModelFactory by instance()
    private lateinit var viewModel: GalleryViewModel
    private lateinit var dispose: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeUI()
        subscribes()
    }

    override fun onItemClick(view: View, imageEntry: ImageEntry) {
        val intent = Intent(this, ImageActivity::class.java)
        intent.putExtra("image", imageEntry)
        val option = ActivityOptions.makeSceneTransitionAnimation(
            this, view,
            "imageTransition"
        )
        startActivity(intent, option.toBundle())
    }

    private fun initializeUI() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(GalleryViewModel::class.java)
        viewModel.setCallBack(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_gallery)
        binding.lifecycleOwner = this@GalleryActivity
        binding.viewModel = viewModel

        sv_search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.searchQuery.postValue(newText)
                return false
            }
        })
    }

    private fun subscribes() {
        viewModel.searchQuery.observe(this, Observer { it ->
            if (it.isNotEmpty()) {
                viewModel.sendRequest(it).observe(
                    this@GalleryActivity,
                    Observer {
                        viewModel.setImagesInAdapter(it)
                    })
            }
        })

        dispose = viewModel.eventState.subscribe {
            if (it.state)
                viewModel.loadingVisibility.postValue(View.VISIBLE)
            else
                viewModel.loadingVisibility.postValue(View.GONE)

            if (it.message.isNotEmpty())
                viewModel._message.postValue(it.message)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dispose.dispose()
    }
}