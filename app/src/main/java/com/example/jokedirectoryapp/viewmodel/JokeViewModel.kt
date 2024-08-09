package com.example.jokedirectoryapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jokedirectoryapp.domain.JokeAppRepository
import com.example.jokedirectoryapp.model.JokeUIModel
import com.example.jokedirectoryapp.utils.toJokeUIModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class JokeViewModel(
    private val repository: JokeAppRepository
): ViewModel() {

    private val _jokeUIState = MutableLiveData<JokeUIState>()
    val jokeUIState: LiveData<JokeUIState> = _jokeUIState

    init {
        getNextJoke()
    }

    fun getNextJoke() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val getJokeResponseFlow = repository.getNextJoke()
                getJokeResponseFlow.collect {
                    it.onSuccess {
                        Log.d("API Success", it.toString())
                        _jokeUIState.postValue(
                            JokeUIState.Success(it.toJokeUIModel())
                        )
                    }
                    it.onFailure {
                        Log.e("API Error while Fetching", it.toString())
                    }
                }
            }
        }
    }

    private fun getCurrJokeId(): Int? {
        jokeUIState.value?.let {
            return when(it) {
                is JokeUIState.Success -> it.data.id
            }
        } ?: return null

    }

    fun markFav() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getCurrJokeId()?.let {
                    repository.markFav(it)
                }
            }
        }
    }

    sealed interface JokeUIState {
        data class Success(val data: JokeUIModel): JokeUIState
    }

}