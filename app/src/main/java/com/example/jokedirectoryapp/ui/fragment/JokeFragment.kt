package com.example.jokedirectoryapp.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.example.jokedirectoryapp.Module.Injection
import com.example.jokedirectoryapp.R
import com.example.jokedirectoryapp.databinding.FragmentJokeBinding
import com.example.jokedirectoryapp.viewmodel.JokeViewModel
import com.example.jokedirectoryapp.viewmodel.factory.JokeViewModelFactory

class JokeFragment: Fragment(R.layout.fragment_joke) {

    private val viewModel by viewModels<JokeViewModel> {
        JokeViewModelFactory(Injection.repository)
    }
    private var binding: FragmentJokeBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding =DataBindingUtil.bind<FragmentJokeBinding>(view)?.apply {
            addLiveDataObservers()
            addClickListeners()
        }
    }

    private fun FragmentJokeBinding.addClickListeners() {
        nextJoke.setOnClickListener {
            viewModel.getNextJoke()
        }
        markFav.setOnClickListener {
            viewModel.markFav()
        }
    }

    private fun addLiveDataObservers() {
        viewModel.jokeUIState.observe(viewLifecycleOwner) {
            when(it) {
                is JokeViewModel.JokeUIState.Success -> {
                    binding?.jokeText?.text = it.data.jokeString
                }
            }
        }
    }


}