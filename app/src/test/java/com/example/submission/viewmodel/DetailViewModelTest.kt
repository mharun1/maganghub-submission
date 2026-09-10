package com.example.submission.viewmodel

import com.example.submission.Dummy
import com.example.submission.MainDispatcherRule
import com.example.submission.data.repository.TvShowRepository
import com.example.submission.ui.common.UiState
import com.example.submission.ui.detail.DetailViewModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    private lateinit var repository: TvShowRepository
    private lateinit var viewModel: DetailViewModel

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setup() {
        repository = Mockito.mock()
    }

    @Test
    fun `when getDetailShow success, state become Success with data`() = runTest {
        val dummyDetailShow = Dummy.generateDummyDetailShow(1)
        whenever(repository.getDetailShow(1)).thenReturn(dummyDetailShow)

        viewModel = DetailViewModel(repository)
        viewModel.getDetailShow(1)

        val state = viewModel.uiState.value
        assertTrue(state is UiState.Success)
        assertEquals(dummyDetailShow, (state as UiState.Success).data)
    }

    @Test
    fun `when getDetailShow throw, state become Error`() = runTest {
        whenever(repository.getDetailShow(1)).thenThrow(RuntimeException("Error"))

        viewModel = DetailViewModel(repository)
        viewModel.getDetailShow(1)

        val state = viewModel.uiState.value
        assertTrue(state is UiState.Error)
        assertEquals("Error", (state as UiState.Error).errorMessage)
    }
}