package com.example.submission.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.submission.data.repository.Repository
import com.example.submission.data.repository.TvShowRepository
import com.example.submission.ui.list.ListViewModel

//class TvShowViewModelFactory(
//    private var repository: TvShowRepository
//) : ViewModelProvider.Factory {
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(ListViewModel::class.java))
//            return ListViewModel(repository) as T
//        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
//    }
//}