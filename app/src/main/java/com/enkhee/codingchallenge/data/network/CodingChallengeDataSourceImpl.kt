package com.enkhee.codingchallenge.data.network

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.enkhee.codingchallenge.data.network.response.EventState
import com.enkhee.codingchallenge.data.network.response.GallerySearchResponse
import com.enkhee.codingchallenge.internal.NoConnectivityException
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit

class CodingChallengeDataSourceImpl(
    private val codingChallengeApiService: CodingChallengeApiService
) : CodingChallengeDataSource {

    private val _downloadGallery = MutableLiveData<GallerySearchResponse>()
    override val downloadGallery: LiveData<GallerySearchResponse>
        get() = _downloadGallery

    private var _eventState = EventState()
    private val subject: BehaviorSubject<EventState> = BehaviorSubject.create()
    override var eventState: Observable<EventState> = subject


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
            _eventState.message = "No internet connection."
            subject.onNext(_eventState)
        }
    }

    private fun onStart() {
        _eventState.message = ""
        _eventState.state = true
        subject.onNext(_eventState)
    }

    private fun onFinish() {
        _eventState.state = false
        subject.onNext(_eventState)
    }

    private fun onError(error: Throwable) {
        _eventState.message = "No internet connection."
        subject.onNext(_eventState)
    }

    private fun onSuccess(result: GallerySearchResponse) {
        _downloadGallery.postValue(result)
    }
}