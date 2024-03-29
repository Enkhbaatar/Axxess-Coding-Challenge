package com.enkhee.codingchallenge.ui.image

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.enkhee.codingchallenge.R
import com.enkhee.codingchallenge.data.db.entiry.ImageEntry
import com.enkhee.codingchallenge.databinding.ActivityImageBinding
import com.enkhee.codingchallenge.ui.base.ScopedActivity
import org.jetbrains.annotations.TestOnly
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ImageActivity : ScopedActivity(), KodeinAware {
    private lateinit var binding: ActivityImageBinding
    override val kodein by kodein()
    private val viewModelFactory: ImageViewModelFactory by instance()
    private lateinit var viewModel: ImageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeUI()
    }

    private fun initializeUI() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ImageViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_image)
        binding.lifecycleOwner = this@ImageActivity
        binding.viewModel = viewModel

        viewModel.image = intent.getSerializableExtra("image") as ImageEntry

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = if (viewModel.image.title.isNullOrEmpty()) "No Title" else viewModel.image.title
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    @TestOnly
    fun setTestViewModel(imageViewModel: ImageViewModel) {
        this.viewModel = imageViewModel
        initializeUI()
    }
}