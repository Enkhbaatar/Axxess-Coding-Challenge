package com.enkhee.codingchallenge.ui.gallery

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.enkhee.codingchallenge.R
import com.enkhee.codingchallenge.databinding.ActivityMainBinding
import com.enkhee.codingchallenge.ui.base.ScopedActivity
import com.enkhee.codingchallenge.ui.image.ImageActivity
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainActivity : ScopedActivity(), KodeinAware {
    private lateinit var binding: ActivityMainBinding
    override val kodein by kodein()
    private val viewModelFactory: GalleryViewModelFactory by instance()
    private lateinit var viewModel: GalleryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeUI()
    }

    private fun initializeUI() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(GalleryViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.selectedImage.observe(this@MainActivity, Observer {
            val intent = Intent(this@MainActivity, ImageActivity::class.java)
            intent.putExtra("image", it)
            this@MainActivity.startActivity(intent)
        })

        viewModel.selectedImage.observe(this@MainActivity, Observer {
            val intent = Intent(this@MainActivity, ImageActivity::class.java)
            intent.putExtra("image", it)
            this@MainActivity.startActivity(intent)
        })
    }
}
