package com.raza.acronymsmeaning.repository

import com.raza.acronymsmeaning.api.ApiManager
import com.raza.acronymsmeaning.model.Meanings
import com.raza.acronymsmeaning.utils.NetworkResult
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class AcronymsRepositoryTest {
    @Mock
    private lateinit var mainRepository: AcronymsRepository

    @Mock
    lateinit var apiManager: ApiManager

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

    }

    @Test
    fun getMeaningsData()= runTest {
        val meaningsData = Meanings()
        Mockito.`when`(apiManager.getAcronyms("sf")).thenReturn(
            Response.success(meaningsData)
        )

        val response = mainRepository.getAcronyms("sf")
        Assert.assertEquals(NetworkResult.Success(meaningsData), response)
    }
}