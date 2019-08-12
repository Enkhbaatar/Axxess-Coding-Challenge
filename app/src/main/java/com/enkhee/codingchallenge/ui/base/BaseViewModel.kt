package com.enkhee.codingchallenge.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    val loadingVisibility = MutableLiveData<Int>()
    val _message = MutableLiveData<String>()
    val message: LiveData<String>
        get() = _message

}