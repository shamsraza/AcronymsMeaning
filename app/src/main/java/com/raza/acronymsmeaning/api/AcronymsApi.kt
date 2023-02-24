package com.raza.acronymsmeaning.api

import com.raza.acronymsmeaning.model.Meanings
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AcronymsApi {
    @GET("dictionary.py")
    suspend fun getMeaningsData(
        @Query("sf") sortForm: String
    ): Response<Meanings>
}