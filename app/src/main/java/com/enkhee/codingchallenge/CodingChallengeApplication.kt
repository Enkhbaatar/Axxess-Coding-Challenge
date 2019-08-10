package com.enkhee.codingchallenge

import android.app.Application
import android.content.Context
import com.enkhee.codingchallenge.data.db.CommentDatabase
import com.enkhee.codingchallenge.data.network.*
import com.enkhee.codingchallenge.data.repository.CommentRepository
import com.enkhee.codingchallenge.data.repository.CommentRepositoryImpl
import com.enkhee.codingchallenge.data.repository.GalleryRepository
import com.enkhee.codingchallenge.data.repository.GalleryRepositoryImpl
import com.enkhee.codingchallenge.ui.gallery.GalleryViewModelFactory
import com.enkhee.codingchallenge.ui.image.ImageViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class CodingChallengeApplication: Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@CodingChallengeApplication))

        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind() from singleton { CodingChallengeApiService(instance()) }
        bind() from singleton { CommentDatabase(instance())}
        bind() from singleton {instance<CommentDatabase>().commentDao()}
        bind<CodingChallengeDataSource>() with singleton { CodingChallengeDataSourceImpl(instance()) }
        bind<GalleryRepository>() with singleton { GalleryRepositoryImpl(instance()) }
        bind() from provider { GalleryViewModelFactory(instance()) }
        bind<CommentRepository>() with singleton { CommentRepositoryImpl(instance()) }
        bind() from provider { ImageViewModelFactory(instance()) }
    }
}