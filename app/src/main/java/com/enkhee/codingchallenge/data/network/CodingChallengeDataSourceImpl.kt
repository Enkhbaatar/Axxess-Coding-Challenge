package com.enkhee.codingchallenge.data.network

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.enkhee.codingchallenge.data.network.response.GallerySearchResponse
import com.enkhee.codingchallenge.internal.NoConnectivityException
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class CodingChallengeDataSourceImpl(
    private val codingChallengeApiService: CodingChallengeApiService
) : CodingChallengeDataSource {



    private val _downloadGallery = MutableLiveData<GallerySearchResponse>()
    override val downloadGallery: LiveData<GallerySearchResponse>
        get() = _downloadGallery

    @SuppressLint("CheckResult")
    override fun fetchGallery(value: String) {
        try {
            codingChallengeApiService.searchGallery(value)
                .debounce(250, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onStart() }
                .doOnTerminate { onFinish() }
                .subscribe(
                    { result -> onSuccess(result) },
                    { error -> onError(error) }
                )
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection.", e)
        }
    }

    private fun onStart() {

    }

    private fun onFinish() {

    }

    private fun onError(error: Throwable) {
        Log.e("GetGallery", error.toString())
        Log.v("GetGallery", "Error")
    }

    private fun onSuccess(result: GallerySearchResponse) {
        Log.v("GetGallery", "Success")
        _downloadGallery.postValue(result)
    }
}