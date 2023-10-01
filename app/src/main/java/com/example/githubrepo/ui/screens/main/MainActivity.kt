package com.example.githubrepo.ui.screens.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.content.ContextCompat.startActivity
import com.example.githubrepo.R
import com.example.githubrepo.ui.screens.details.DetailsActivity
import com.example.githubrepo.ui.screens.main.components.ReposList
import com.example.githubrepo.ui.theme.GitHubRepoTheme
import com.example.githubrepo.ui.utils.LoadingBar
import com.example.githubrepo.ui.utils.ShowToast
import com.example.githubrepo.ui.utils.Status
import com.example.githubrepo.utils.AUTHOR_NAME
import com.example.githubrepo.utils.Network
import com.example.githubrepo.utils.REPO_NAME
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitHubRepoTheme {
                MainScreen(viewModel)
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
fun MainScreen(viewModel: MainViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        maxLines = 1,
                        style = MaterialTheme.typography.headlineLarge
                    )
                },
            )
        },
        modifier = Modifier.fillMaxSize(),
    ) {
        MainScreenContent(viewModel, Modifier.padding(it))
    }
}

@Composable
fun MainScreenContent(viewModel: MainViewModel, modifier: Modifier) {
    val context = LocalContext.current
    viewModel.state.value.let {
        when (it.status) {
            Status.LOADING -> LoadingBar()
            Status.SUCCESS -> ReposList(it.data ?: emptyList(), modifier) { name, author ->
                val intent = Intent(context, DetailsActivity::class.java)
                intent.putExtra(REPO_NAME, name)
                intent.putExtra(AUTHOR_NAME, author)
                startActivity(context, intent, null)
            }

            Status.FAILURE -> ShowToast(it.message)
        }
    }
}