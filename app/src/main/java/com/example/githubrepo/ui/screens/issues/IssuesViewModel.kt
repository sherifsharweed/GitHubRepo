package com.example.githubrepo.ui.screens.issues

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubrepo.data.models.IssueResponse
import com.example.githubrepo.data.repos.ReposRepo
import com.example.githubrepo.ui.utils.DataStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class IssuesViewModel @Inject constructor(private val reposRepo: ReposRepo) : ViewModel() {

    val state = mutableStateOf<DataStatus<List<IssueResponse>>>(DataStatus.loading())

    fun getRepoIssues(repoName: String?, authorName: String?) {
        repoName?.let {
            authorName?.let {
                viewModelScope.launch {
                    withContext(Dispatchers.IO) {
                        reposRepo.getRepoIssues(repoName, authorName).collect {
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