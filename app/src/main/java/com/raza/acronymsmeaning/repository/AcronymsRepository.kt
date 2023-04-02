package com.raza.acronymsmeaning.repository

import com.raza.acronymsmeaning.api.ApiManager
import com.raza.acronymsmeaning.model.Meanings
import com.raza.acronymsmeaning.utils.NetworkResult
object AcronymsRepository {
    private val apiManager: ApiManager = ApiManager()

    suspend fun getAcronyms(sortForm: String): NetworkResult<Meanings> {
        val response = apiManager.getAcronyms(sortForm)
        return if (response.isSuccessful) {
            val responseBody = response.body()
            if (responseBody != null) {
                NetworkResult.Success(responseBody)
            } else {
                NetworkResult.Error(response)
            }
        } else {
            NetworkResult.Error(response)
        }
    }
}