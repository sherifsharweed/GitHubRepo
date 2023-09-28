package com.example.githubrepo.ui.screens.main.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.githubrepo.data.models.RepoResponse


@Composable
fun ReposList(data: List<RepoResponse>) {
    LazyColumn {
        items(data.size) { index ->
            Text(text = data[index].description ?: "")
            Divider(Modifier.padding(vertical = 10.dp))
        }
    }
}