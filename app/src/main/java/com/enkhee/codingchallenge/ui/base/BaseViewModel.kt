package com.enkhee.codingchallenge.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel: ViewModel() {
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val message: MutableLiveData<String> = MutableLiveData()

}