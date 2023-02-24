package com.raza.acronymsmeaning.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.raza.acronymsmeaning.api.ApiManager
import com.raza.acronymsmeaning.model.Meanings
import com.raza.acronymsmeaning.utils.TestCoroutineRule
import com.raza.acronymsmeaning.viewmodel.AcronymsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var apiManager: ApiManager

    @Mock
    private lateinit var largeFormListObserver:Observer<List<String>>

    @Test
    fun testAcronyms() {
        testCoroutineRule.runBlockingTest {
            Mockito.doReturn(emptyList<Meanings>())
                .`when`(apiManager)
                .getAcronyms("sf")
            val viewModel = AcronymsViewModel()
            viewModel.largeFormList.observeForever(largeFormListObserver)
            Mockito.verify(apiManager).getAcronyms("sf")
            Mockito.verify(largeFormListObserver).onChanged(emptyList())
            viewModel.largeFormList.removeObserver(largeFormListObserver)
        }
    }

}