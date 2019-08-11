package com.enkhee.codingchallenge.ui.gallery

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.enkhee.codingchallenge.R
import com.enkhee.codingchallenge.databinding.ActivityMainBinding
import com.enkhee.codingchallenge.ui.base.ScopedActivity
import com.enkhee.codingchallenge.ui.image.ImageActivity
import io.reactivex.disposables.Disposable
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainActivity : ScopedActivity(), KodeinAware {
    private lateinit var binding: ActivityMainBinding
    override val kodein by kodein()
    private val viewModelFactory: GalleryViewModelFactory by instance()
    private lateinit var viewModel: GalleryViewModel
    private lateinit var dispose: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeUI()
        subscribes()
    }

    private fun initializeUI() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(GalleryViewModel::class.java)
        binding.viewModel = viewModel
    }

    private fun subscribes() {
        viewModel.selectedImage.observe(this@MainActivity, Observer {
            val intent = Intent(this@MainActivity, ImageActivity::class.java)
            intent.putExtra("image", it)
            val option = ActivityOptions.makeSceneTransitionAnimation(
                this@MainActivity,
                viewModel.selectedView,
                "imageTransition"
            )
            this@MainActivity.startActivity(intent, option.toBundle())
        })

        dispose = viewModel.eventState.subscribe {
            if (it.state)
                viewModel.loadingVisibility.postValue(View.VISIBLE)
            else
                viewModel.loadingVisibility.postValue(View.GONE)

            if (it.message.isNotEmpty())
                viewModel.message.postValue(it.message)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dispose.dispose()
    }
}
