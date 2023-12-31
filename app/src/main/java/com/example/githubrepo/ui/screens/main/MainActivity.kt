package com.example.githubrepo.ui.screens.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import com.example.githubrepo.ui.screens.details.DetailsActivity
import com.example.githubrepo.ui.screens.main.components.ReposList
import com.example.githubrepo.ui.screens.main.components.SearchAppBar
import com.example.githubrepo.ui.theme.GitHubRepoTheme
import com.example.githubrepo.ui.utils.BaseActivity
import com.example.githubrepo.ui.utils.LoadingBar
import com.example.githubrepo.ui.utils.ShowToast
import com.example.githubrepo.ui.utils.Status
import com.example.githubrepo.utils.AUTHOR_NAME
import com.example.githubrepo.utils.REPO_NAME
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitHubRepoTheme(darkTheme = darkModeState) {
                MainScreen(darkModeState, viewModel, this::setDarkMode)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    darkModeState: Boolean,
    viewModel: MainViewModel,
    onChangeDarkMode: (Boolean) -> Unit
) {
    Scaffold(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(it)) {
            Row(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SearchAppBar(
                    text = viewModel.searchTextState,
                    onTextChange = viewModel::updateSearchText,
                    onSearchClicked = viewModel::onSearchRepos,
                    onCloseSearch = viewModel::onClearSearch,
                    Modifier
                        .height(60.dp)
                        .background(MaterialTheme.colorScheme.secondary, shape = CircleShape)
                        .padding(horizontal = 10.dp)
                        .weight(1f)
                )
                Spacer(modifier = Modifier.width(2.dp))
                Switch(
                    checked = darkModeState,
                    onCheckedChange = onChangeDarkMode
                )
            }
            MainScreenContent(viewModel)
        }
    }
}

@Composable
fun MainScreenContent(viewModel: MainViewModel) {
    val context = LocalContext.current
    viewModel.state.value.let {
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