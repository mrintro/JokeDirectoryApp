package com.example.jokedirectoryapp.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.jokedirectoryapp.domain.JokeAppRepository
import com.example.jokedirectoryapp.viewmodel.JokeViewModel

class JokeViewModelFactory(
    private val repository: JokeAppRepository
): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        return JokeViewModel(repository) as T
    }
}