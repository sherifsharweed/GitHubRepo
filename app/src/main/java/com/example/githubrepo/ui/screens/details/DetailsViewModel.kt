package com.example.githubrepo.ui.screens.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubrepo.data.models.SingleRepoInfo
import com.example.githubrepo.data.repos.ReposRepo
import com.example.githubrepo.ui.utils.DataStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailsViewMode @Inject constructor(private val reposRepo: ReposRepo) : ViewModel() {

    val state = mutableStateOf<DataStatus<SingleRepoInfo>>(DataStatus.loading())

    fun getRepoInfo(repoName: String?, authorName: String?) {
        repoName?.let {
            authorName?.let {
                viewModelScope.launch {
                    withContext(Dispatchers.IO) {
                        reposRepo.getSingleRepoInfo(repoName, authorName).collect {
                            withContext(Dispatchers.Main) {
                                state.value = it
                            }
                        }
                    }
                }
            }
        }
    }
}
