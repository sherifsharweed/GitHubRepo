package com.example.githubrepo.ui.screens.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubrepo.data.models.RepoResponse
import com.example.githubrepo.data.repos.ReposRepo
import com.example.githubrepo.ui.utils.DataStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val reposRepo: ReposRepo) : ViewModel() {

    val state = mutableStateOf<DataStatus<List<RepoResponse>>>(DataStatus.loading())

    init {
        getRepoList()
    }

    private fun getRepoList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                reposRepo.getAllRepos().collect {
                    withContext(Dispatchers.Main) {
                        state.value = it
                    }
                }
            }
        }
    }
}