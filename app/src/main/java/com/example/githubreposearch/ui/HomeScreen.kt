package com.example.githubreposearch.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.githubreposearch.ui.components.RepositoryList
import com.example.githubreposearch.ui.viewmodel.HomeViewModel

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel: HomeViewModel = hiltViewModel()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val repositories by viewModel.repositories.collectAsState(initial = emptyList())

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        SearchBar(
            query = searchQuery,
            onQueryChange = { viewModel.updateSearchQuery(it) },
            onSearch = { viewModel.searchRepositories() }
        )

        Spacer(modifier = Modifier.height(16.dp))

        RepositoryList(
            repositories = repositories,
            onItemClick = { navController.navigate("repo_details/${it.id}") }
        )
    }
}

@Composable
fun SearchBar(
    query: TextFieldValue,
    onQueryChange: (TextFieldValue) -> Unit,
    onSearch: () -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        BasicTextField(
            value = query,
            onValueChange = onQueryChange,
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
                .background(MaterialTheme.colors.surface, shape = MaterialTheme.shapes.small)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Button(onClick = onSearch) {
            Text("Search")
        }
    }
}
