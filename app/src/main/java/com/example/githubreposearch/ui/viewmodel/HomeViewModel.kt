package com.example.githubreposearch.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubreposearch.repository.GitHubRepository
import com.example.githubreposearch.model.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: GitHubRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _repositories = MutableStateFlow<List<Repository>>(emptyList())
    val repositories: StateFlow<List<Repository>> = _repositories

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun searchRepositories() {
        viewModelScope.launch {
            val repos = repository.searchRepositories(_searchQuery.value)
            _repositories.value = repos
        }
    }
}
