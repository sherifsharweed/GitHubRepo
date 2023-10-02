package com.example.githubrepo.ui.screens.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
    var searchTextState by mutableStateOf("")

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

    fun updateSearchText(text: String) {
        searchTextState = text
    }

    fun onSearchRepos(text: String) {
        if (text.isNotEmpty()) {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    reposRepo.getSearchedRepos(text).collect {
                        withContext(Dispatchers.Main) {
                            state.value = it
                        }
                    }
                }
            }
        }
    }

    fun onClearSearch() {
        if (searchTextState.isNotEmpty()) {
            searchTextState = ""
            getRepoList()
        }
    }
}