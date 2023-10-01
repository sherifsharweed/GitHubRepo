package com.example.githubrepo.ui.screens.details.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.githubrepo.R
import com.example.githubrepo.data.models.SingleRepoInfo

@Composable
fun DetailsOfSingleRepo(data: SingleRepoInfo?) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier.verticalScroll(state = scrollState)
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
            Row(Modifier.padding(top = 8.dp)) {
                Text(
                    data?.owner?.login ?: ""
                )
            }
            Text(
                data?.description ?: "",
                modifier = Modifier.padding(top = 10.dp)
            )
            /*  Row(Modifier.padding(top = 8.dp)) {
                Text(
                    data?.source?.name ?: "", style = date
                )
            }*/
        }
        Spacer(modifier = Modifier.weight(1f))
        /* Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            Button(
                onClick = { onWebsiteClick(article?.url) },
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
                modifier = Modifier.padding(vertical = 16.dp)
            ) {
                Text(text = stringResource(id = R.string.go_to_website), style = buttonTitle)
            }
        }*/

    }
}