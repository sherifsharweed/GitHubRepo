package com.example.githubrepo.ui.screens.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.githubrepo.R
import com.example.githubrepo.data.models.Owner
import com.example.githubrepo.data.models.RepoResponse


@Composable
fun ReposList(
    data: List<RepoResponse>,
    onItemSelect: (String?, String?) -> Unit
) {
    LazyColumn(modifier = Modifier.padding(top = 4.dp)) {
        items(data.size) { index ->
            SingleRepoItem(data[index], onItemSelect)
            Divider(Modifier.padding(vertical = 8.dp))
        }
    }
}

@Composable
fun SingleRepoItem(repoItem: RepoResponse, onItemSelect: (String?, String?) -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .clickable(onClick = { onItemSelect(repoItem.name, repoItem.owner?.login) }),
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(repoItem.owner?.avatarUrl ?: "")
                .crossfade(true)
                .build(),
            contentDescription = stringResource(R.string.repo_image),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .size(80.dp)
        )
        Column(
            Modifier.padding(start = 8.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Text(
                text = repoItem.name ?: "",
                maxLines = 1,
                style = MaterialTheme.typography.headlineLarge
            )
            Text(
                text = repoItem.owner?.login ?: "",
                maxLines = 1,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = repoItem.description ?: "",
                maxLines = 3,
                style = MaterialTheme.typography.displaySmall
            )
            repoItem.stargazersCount?.toString()?.let { starCount ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.baseline_star_24),
                        contentDescription = "Score"
                    )
                    Text(text = starCount, style = MaterialTheme.typography.displayLarge)

                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewItem() {
    SingleRepoItem(
        repoItem = RepoResponse(
            name = "sesqdre",
            owner = Owner(
                login = "sfadfasdf",
                avatarUrl = "https://avatars.githubusercontent.com/u/81463583?v=4"
            ),
            description = "QWNDIFCUJWNMDCU9WIENWIECNWFCQWNDIFCUJWNMDCU9WIENWIECNWFCQWNDIFCUJWNMDCU9WIENWIECNWFCQWNDIFCUJWNMDCU9WIENWIECNWFCQWNDIFCUJWNMDCU9WIENWIECNWFCQWNDIFCUJWNMDCU9WIENWIECNWFCQWNDIFCUJWNMDCU9WIENWIECNWFC"
        )
    ) { name, aithor ->
    }
}
