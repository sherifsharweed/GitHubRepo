package com.example.githubrepo.ui.screens.details

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.githubrepo.R
import com.example.githubrepo.utils.AUTHOR_NAME
import com.example.githubrepo.utils.REPO_NAME
import com.example.githubrepo.ui.screens.details.components.DetailsOfSingleRepo
import com.example.githubrepo.ui.screens.issues.IssuesScreen
import com.example.githubrepo.ui.theme.GitHubRepoTheme
import com.example.githubrepo.ui.utils.LoadingBar
import com.example.githubrepo.ui.utils.ShowToast
import com.example.githubrepo.ui.utils.Status
import com.example.githubrepo.utils.Network
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : ComponentActivity() {
    private val viewModel by viewModels<DetailsViewMode>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repoName = intent.getStringExtra(REPO_NAME)
        val authorName = intent.getStringExtra(AUTHOR_NAME)
        viewModel.getRepoInfo(repoName, authorName)
        setContent {
            GitHubRepoTheme {
                DetailsScreen(repoName, viewModel, { finish() }) {
                    val intent = Intent(baseContext, IssuesScreen::class.java)
                    intent.putExtra(REPO_NAME, repoName)
                    intent.putExtra(AUTHOR_NAME, authorName)
                    startActivity(intent)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (!Network.hasInternet(baseContext)) {
            viewModel.passErrorToState(getString(R.string.no_internet_connection))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    repoName: String?,
    viewModel: DetailsViewMode, onBackButtonClick: () -> Unit, onIssuesButtonClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = repoName ?: "",
                        maxLines = 1,
                        style = MaterialTheme.typography.headlineLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackButtonClick) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.back_button),
                            tint = Color.Black
                        )
                    }
                }
            )
        },
        modifier = Modifier.fillMaxSize(),
    ) {
        DetailsScreenContent(viewModel, onIssuesButtonClick, Modifier.padding(it))

    }
}

@Composable
fun DetailsScreenContent(
    viewModel: DetailsViewMode,
    onIssuesButtonClick: () -> Unit,
    modifier: Modifier
) {
    viewModel.state.value.let {
        when (it.status) {
            Status.LOADING -> LoadingBar()
            Status.SUCCESS -> DetailsOfSingleRepo(it.data, onIssuesButtonClick, modifier)
            Status.FAILURE -> ShowToast(it.message)
        }
    }
}

