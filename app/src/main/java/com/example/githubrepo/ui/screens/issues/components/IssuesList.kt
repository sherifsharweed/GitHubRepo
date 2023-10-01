package com.example.githubrepo.ui.screens.issues.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.githubrepo.data.models.IssueResponse
import com.example.githubrepo.ui.utils.formatDate


@Composable
fun IssuesList(data: List<IssueResponse>, modifier: Modifier) {
    LazyColumn(modifier = modifier) {
        items(data.size) { index ->
            SingleIssueItem(data[index])
            Divider(Modifier.padding(vertical = 8.dp))
        }
    }
}

@Composable
fun SingleIssueItem(issueResponse: IssueResponse) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Row() {
                Text(
                    text = "#${issueResponse.number?.toString() ?: ""} ",
                    style = MaterialTheme.typography.headlineLarge
                )
                Text(
                    text = issueResponse.title ?: "",
                    maxLines = 2,
                    style = MaterialTheme.typography.headlineLarge
                )
            }
            Text(text = issueResponse.state ?: "", style = MaterialTheme.typography.displayLarge)
            Text(
                "by ${issueResponse.user?.login ?: ""}",
                style = MaterialTheme.typography.displayLarge
            )
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                Text(
                    "On ${formatDate(issueResponse.createdAt)}",
                    style = MaterialTheme.typography.displayLarge
                )
            }
        }
    }
}
