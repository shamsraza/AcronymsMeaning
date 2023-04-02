package com.raza.acronymsmeaning.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raza.acronymsmeaning.model.Meanings
import com.raza.acronymsmeaning.repository.AcronymsRepository
import com.raza.acronymsmeaning.utils.NetworkResult
import com.raza.acronymsmeaning.utils.ValUtil
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class AcronymsViewModel : ViewModel() {
   private val _largeFormList = MutableLiveData<List<String>>()
    val largeFormList :LiveData<List<String>>
    get() = _largeFormList
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage
    val loading = MutableLiveData(View.GONE)
    val rvVisibility = MutableLiveData(View.GONE)

    fun getAcronyms(sortForm: String,coroutineDispatcher: CoroutineDispatcher) {
        viewModelScope.launch(coroutineDispatcher) {
            loading.postValue(View.VISIBLE)
            try {
                when (val response = AcronymsRepository.getAcronyms(sortForm)) {
                    is NetworkResult.Success -> {
                        getLargeFormsList(response.data)
                        loading.postValue(View.GONE)
                    }
                    is NetworkResult.Error -> {
                        onError(response.toString())
                    }
                }
            } catch (ex: UnknownHostException) {
                onError(ValUtil.NETWORK_ERROR_MESSAGE)
            } catch (ex: java.lang.Exception) {
                onError(ex.stackTraceToString())
            }
        }
    }


    private fun getLargeFormsList(meaningsData: Meanings) {
        if ((meaningsData.isNotEmpty()) && (meaningsData[0].lfs.isNotEmpty())) {
            val tempLfArrayList = mutableListOf<String>()
            for (lfItem in meaningsData[0].lfs) {
                tempLfArrayList.add(lfItem.lf)
            }
            _largeFormList.postValue(tempLfArrayList)
        } else {
            onError(ValUtil.RESPONSE_ERROR_MESSAGE)
        }
    }

    private fun onError(message: String) {
        _errorMessage.postValue(message)
        loading.postValue(View.GONE)
    }
}