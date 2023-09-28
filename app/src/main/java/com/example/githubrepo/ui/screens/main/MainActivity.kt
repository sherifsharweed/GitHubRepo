package com.example.githubrepo.ui.screens.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.githubrepo.ui.screens.main.components.ReposList
import com.example.githubrepo.ui.theme.GitHubRepoTheme
import com.example.githubrepo.ui.utils.LoadingBar
import com.example.githubrepo.ui.utils.ShowToast
import com.example.githubrepo.ui.utils.Status
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
}

@Composable
fun MainScreen(viewModel: MainViewModel) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        MainScreenContent(viewModel)
    }
}

@Composable
fun MainScreenContent(viewModel: MainViewModel) {
    viewModel.state.value.let {
        when (it.status) {
            Status.LOADING -> LoadingBar()
            Status.SUCCESS -> ReposList(it.data ?: emptyList())
            Status.FAILURE -> ShowToast(it.message)
        }
    }
}