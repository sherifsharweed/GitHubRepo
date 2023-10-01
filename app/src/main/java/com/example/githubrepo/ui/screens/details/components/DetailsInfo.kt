package com.example.githubrepo.ui.screens.details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.githubrepo.R
import com.example.githubrepo.data.models.SingleRepoInfo

@Composable
fun DetailsOfSingleRepo(
    data: SingleRepoInfo?,
    onIssuesButtonClick: () -> Unit, modifier: Modifier
) {
    val scrollState = rememberScrollState()
    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .verticalScroll(state = scrollState)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data?.owner?.avatarUrl).crossfade(true)
                    .build(),
                contentDescription = stringResource(R.string.repo_image),
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth()
            )
            Column(
                Modifier
                    .padding(horizontal = 8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text(
                        stringResource(id = R.string.author),
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        data?.owner?.login ?: "", style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    data?.stargazersCount?.toString()?.let { starCount ->
                        Image(
                            painter = painterResource(id = R.drawable.baseline_star_24),
                            contentDescription = "Score"
                        )
                        Text(text = starCount)
                    }
                }
                Text(
                    data?.description ?: "", style = MaterialTheme.typography.displayLarge,
                    modifier = Modifier.padding(top = 8.dp)
                )
                data?.language?.let {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 4.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(10.dp)
                                .clip(CircleShape)
                                .background(Color.Blue)
                        )
                        Text(
                            text = it,
                            style = MaterialTheme.typography.displayLarge,
                            modifier = Modifier.padding(start = 3.dp)
                        )
                        data.forksCount?.toString()?.let { forkCount ->
                            Spacer(modifier = Modifier.weight(1f))
                            Icon(
                                imageVector = Icons.Rounded.Share,
                                tint = Color.Gray,
                                contentDescription = "Fork",
                                modifier = Modifier.size(20.dp)
                            )
                            Text(
                                text = forkCount,
                                style = MaterialTheme.typography.displayLarge,
                                modifier = Modifier.padding(start = 3.dp)
                            )
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                Button(
                    onClick = onIssuesButtonClick,
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                    modifier = Modifier.padding(vertical = 16.dp)
                ) {
                    Text(text = stringResource(id = R.string.show_issues), color = Color.White)
                }
            }
        }
    }
}