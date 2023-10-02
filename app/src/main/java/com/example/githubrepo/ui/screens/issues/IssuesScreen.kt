package com.example.githubrepo.ui.screens.issues

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.githubrepo.ui.screens.issues.components.IssuesList
import com.example.githubrepo.ui.theme.GitHubRepoTheme
import com.example.githubrepo.ui.utils.AppBarWithTitleAndBackButton
import com.example.githubrepo.ui.utils.BaseActivity
import com.example.githubrepo.ui.utils.LoadingBar
import com.example.githubrepo.ui.utils.ShowToast
import com.example.githubrepo.ui.utils.Status
import com.example.githubrepo.utils.AUTHOR_NAME
import com.example.githubrepo.utils.REPO_NAME
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class IssuesScreen : BaseActivity() {
    private val viewModel by viewModels<IssuesViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repoName = intent.getStringExtra(REPO_NAME)
        val authorName = intent.getStringExtra(AUTHOR_NAME)
        viewModel.getRepoIssues(repoName, authorName)
        setContent {
            GitHubRepoTheme(darkModeState) {
                IssueScreen(repoName, viewModel) { finish() }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IssueScreen(repoName: String?, viewModel: IssuesViewModel, onBackButtonClick: () -> Unit) {
    Scaffold(topBar = {
        AppBarWithTitleAndBackButton(title = repoName, onBackButtonClick = onBackButtonClick)
    }, modifier = Modifier.fillMaxSize()) {
        IssuesScreenContent(viewModel, Modifier.padding(it))

    }
}

@Composable
fun IssuesScreenContent(
    viewModel: IssuesViewModel,
    modifier: Modifier
) {
    viewModel.state.value.let {
        when (it.status) {
            Status.LOADING -> LoadingBar()
            Status.SUCCESS -> IssuesList(it.data ?: emptyList(), modifier)
            Status.FAILURE -> ShowToast(it.message)
        }
    }
}

