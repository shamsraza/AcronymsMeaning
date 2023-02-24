package com.raza.acronymsmeaning.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.raza.acronymsmeaning.model.Meanings
import com.raza.acronymsmeaning.utils.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiManager {
    private val acronymsApi:AcronymsApi
    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client= OkHttpClient.Builder()
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true).addInterceptor(interceptor)
            .build()

        acronymsApi= Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(client).build().create(AcronymsApi::class.java)
    }
    suspend fun getAcronyms( sortForm:String):Response<Meanings>{
      return  acronymsApi.getMeaningsData(sortForm)
    }
}