package com.example.githubrepo.ui.screens.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.example.githubrepo.R
import com.example.githubrepo.ui.screens.details.DetailsActivity
import com.example.githubrepo.ui.screens.main.components.ReposList
import com.example.githubrepo.ui.screens.main.components.SearchAppBar
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
    Scaffold(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.padding(it)) {
            Column {
                Box(modifier = Modifier.padding(horizontal = 8.dp, vertical = 10.dp)) {
                    SearchAppBar(
                        text = viewModel.searchTextState,
                        onTextChange = viewModel::updateSearchText,
                        onSearchClicked = viewModel::onSearchRepos,
                        onCloseSearch = viewModel::onClearSearch
                    )
                }
                MainScreenContent(viewModel)
            }
        }
    }
}

@Composable
fun MainScreenContent(viewModel: MainViewModel) {
    val context = LocalContext.current
    viewModel.state.value.let { it ->
        when (it.status) {
            Status.LOADING -> LoadingBar()
            Status.SUCCESS -> ReposList(
                it.data ?: emptyList(),
            ) { name, author ->
                val intent = Intent(context, DetailsActivity::class.java)
                intent.putExtra(REPO_NAME, name)
                intent.putExtra(AUTHOR_NAME, author)
                startActivity(context, intent, null)
            }

            Status.FAILURE -> ShowToast(it.message)
        }
    }
}