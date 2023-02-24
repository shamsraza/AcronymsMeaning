package com.raza.acronymsmeaning.repository

import com.raza.acronymsmeaning.api.ApiManager
import com.raza.acronymsmeaning.model.Meanings
import kotlinx.coroutines.Deferred
import retrofit2.Response

object  AcronymsRepository {
    private val apiManager: ApiManager = ApiManager()

   suspend fun getAcronyms(sortForm:String): Response<Meanings> {
        return apiManager.getAcronyms(sortForm)
    }
}