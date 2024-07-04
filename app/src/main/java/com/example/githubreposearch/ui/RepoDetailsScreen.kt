package com.example.githubreposearch.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.githubreposearch.ui.viewmodel.RepoDetailsViewModel
import com.google.accompanist.glide.rememberGlidePainter

@Composable
fun RepoDetailsScreen(navController: NavController, repoId: Long) {
    val viewModel: RepoDetailsViewModel = hiltViewModel()
    val repository by viewModel.repository.collectAsState()
    val contributors by viewModel.contributors.collectAsState(initial = emptyList())

    LaunchedEffect(repoId) {
        viewModel.loadRepository(repoId)
    }

    LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        item {
            repository?.let {
                Image(
                    painter = rememberGlidePainter(it.ownerAvatarUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = it.name, style = MaterialTheme.typography.h4)
                Text(text = it.description ?: "No description", style = MaterialTheme.typography.body1)
                Text(
                    text = it.htmlUrl,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.primary,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier.clickable { navController.navigate("webview/${it.htmlUrl}") }
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Contributors", style = MaterialTheme.typography.h5)
            }
        }
        items(contributors) { contributor ->
            ContributorItem(contributor)
        }
    }
}

@Composable
fun ContributorItem(contributor: Contributor) {
    Row(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        Image(
            painter = rememberGlidePainter(contributor.avatarUrl),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .padding(end = 8.dp)
        )
        Column {
            Text(text = contributor.login, style = MaterialTheme.typography.body1)
            Text(text = "${contributor.contributions} contributions", style = MaterialTheme.typography.body2)
        }
    }
}
