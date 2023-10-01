package com.example.githubrepo.ui.screens.details

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.githubrepo.consts.AUTHOR_NAME
import com.example.githubrepo.consts.REPO_NAME
import com.example.githubrepo.ui.screens.details.components.DetailsOfSingleRepo
import com.example.githubrepo.ui.theme.GitHubRepoTheme
import com.example.githubrepo.ui.utils.LoadingBar
import com.example.githubrepo.ui.utils.ShowToast
import com.example.githubrepo.ui.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : ComponentActivity() {
    private val viewModel by viewModels<DetailsViewMode>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repoName = intent.getStringExtra(REPO_NAME)
        val authorName = intent.getStringExtra(AUTHOR_NAME)
        viewModel.getRepoInfo(repoName,authorName)
        setContent {
            GitHubRepoTheme {
                DetailsScreen(viewModel)
            }
        }
    }
}

@Composable
fun DetailsScreen(viewModel: DetailsViewMode) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        DetailsScreenContent(viewModel)
    }
}

@Composable
fun DetailsScreenContent(viewModel: DetailsViewMode) {
    viewModel.state.value.let {
        when (it.status) {
            Status.LOADING -> LoadingBar()
            Status.SUCCESS -> DetailsOfSingleRepo(it.data)
            Status.FAILURE -> ShowToast(it.message)
        }
    }
}

