package com.example.githubreposearch.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.githubreposearch.model.Repository
import java.lang.reflect.Modifier

@Composable
fun RepositoryList(
    repositories: List<Repository>,
    onItemClick: (Repository) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(repositories) { repository ->
            RepositoryItem(repository, onItemClick)
        }
    }
}

@Composable
fun RepositoryItem(
    repository: Repository,
    onItemClick: (Repository) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onItemClick(repository) },
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = repository.name, style = MaterialTheme.typography.h6)
            Text(text = repository.description ?: "No description", style = MaterialTheme.typography.body2)
        }
    }
}
