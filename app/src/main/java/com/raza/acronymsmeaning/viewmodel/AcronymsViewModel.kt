package com.raza.acronymsmeaning.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.raza.acronymsmeaning.model.Meanings
import com.raza.acronymsmeaning.repository.AcronymsRepository
import com.raza.acronymsmeaning.utils.ValUtil
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class AcronymsViewModel : BaseViewModel() {
    val largeFormList = MutableLiveData<List<String>>()
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() = _errorMessage
    val loading = MutableLiveData(View.GONE)
    val rvVisibility = MutableLiveData(View.GONE)

    fun getAcronyms(sortForm: String) {
        coroutineScope.launch {
            try {
                loading.postValue(View.VISIBLE)
                val response = AcronymsRepository.getAcronyms(sortForm)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        getLargeFormsList(responseBody)
                        loading.postValue(View.GONE)
                    } else {
                        onError(response.toString())
                    }
                } else {
                    onError(response.toString())
                }

            } catch (ex: UnknownHostException) {
                onError(ValUtil.NETWORK_ERROR_MESSAGE)
            } catch (ex: Exception) {
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
            largeFormList.postValue(tempLfArrayList)
        } else {
            onError(ValUtil.RESPONSE_ERROR_MESSAGE)
        }
    }

    private fun onError(message: String) {
        _errorMessage.postValue(message)
        loading.postValue(View.GONE)
    }
}