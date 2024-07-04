package com.example.githubreposearch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.githubreposearch.api.Repository
import com.example.githubreposearch.repository.GitHubRepository
import com.example.githubreposearch.ui.theme.GitHubRepoSearchTheme
import com.example.githubreposearch.viewmodel.GitHubViewModel
import com.example.githubreposearch.viewmodel.GitHubViewModelFactory

class MainActivity : ComponentActivity() {
    private val viewModel: GitHubViewModel by viewModels {
        GitHubViewModelFactory(GitHubRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GitHubRepoSearchTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MainScreen(viewModel)
                }
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: GitHubViewModel) {
    var query by remember { mutableStateOf(TextFieldValue("")) }
    val repositories by viewModel.repositories.observeAsState(emptyList())

    Column(modifier = Modifier.padding(16.dp)) {
        BasicTextField(
            value = query,
            onValueChange = { query = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(Color.Gray.copy(alpha = 0.1f), MaterialTheme.shapes.small)
                .padding(8.dp),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    viewModel.searchRepositories(query.text, 10, 1)
                }
            ),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(repositories) { repository ->
                RepositoryItem(repository = repository, onClick = {
                    viewModel.selectRepository(repository)
                    // Navigate to Repo Details screen
                })
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun RepositoryItem(repository: Repository, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = onClick),
        elevation = 4.dp
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            GlideImage(
                model = repository.owner.avatarUrl,
                contentDescription = "Owner Avatar",
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = repository.name, style = MaterialTheme.typography.h6)
                Text(text = repository.owner.login, style = MaterialTheme.typography.body2)
            }
        }
    }
}
