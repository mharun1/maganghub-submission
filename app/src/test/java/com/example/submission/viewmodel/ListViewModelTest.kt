package com.example.submission.viewmodel

import com.example.submission.Dummy
import com.example.submission.MainDispatcherRule
import com.example.submission.data.repository.TvShowRepository
import com.example.submission.ui.common.UiState
import com.example.submission.ui.list.ListViewModel
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
class ListViewModelTest {
    private lateinit var repository: TvShowRepository
    private lateinit var viewModel: ListViewModel

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setup() {
        repository = Mockito.mock()
    }

    @Test
    fun `when getShows success, state become Success with data`() = runTest {
        val dummyShow = Dummy.generateDummyShows()
        whenever(repository.getShows()).thenReturn(dummyShow)

        viewModel = ListViewModel(repository)
        viewModel.getShows()

        val state = viewModel.uiState.value
        assertTrue(state is UiState.Success)
        assertEquals(dummyShow.size, (state as UiState.Success).data.size)
    }

    @Test
    fun `when getShows throw, state become Error`() = runTest {
        whenever(repository.getShows()).thenThrow(RuntimeException("Error"))

        viewModel = ListViewModel(repository)
        viewModel.getShows()

        val state = viewModel.uiState.value
        assertTrue(state is UiState.Error)
        assertEquals("Error", (state as UiState.Error).errorMessage)
    }
}