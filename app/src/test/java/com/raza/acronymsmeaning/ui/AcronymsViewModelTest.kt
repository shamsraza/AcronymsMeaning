package com.raza.acronymsmeaning.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.raza.acronymsmeaning.api.ApiManager
import com.raza.acronymsmeaning.model.Meanings
import com.raza.acronymsmeaning.utils.MainCoroutineRule
import com.raza.acronymsmeaning.utils.TestCoroutineRule
import com.raza.acronymsmeaning.utils.getOrAwaitValue
import com.raza.acronymsmeaning.viewmodel.AcronymsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class AcronymsViewModelTest {
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var apiManager: ApiManager

    @Test
    fun test_Acronyms() = runTest {
        Mockito.doReturn(emptyList<Meanings>()).`when`(apiManager).getAcronyms("sf")
        val viewModel = AcronymsViewModel()
        viewModel.getAcronyms("sf", mainCoroutineRule.testDispatcher)
        val result = viewModel.largeFormList.getOrAwaitValue()
        Assert.assertEquals(0, result.size)
    }

}