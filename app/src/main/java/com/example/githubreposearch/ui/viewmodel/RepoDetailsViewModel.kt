package com.example.githubreposearch.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubreposearch.model.Contributor
import com.example.githubreposearch.model.Repository
import com.example.githubreposearch.repository.GitHubRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepoDetailsViewModel @Inject constructor(
    private val repository: GitHubRepository
) : ViewModel() {

    private val _repository = MutableStateFlow<Repository?>(null)
    val repository: StateFlow<Repository?> = _repository

    private val _contributors = MutableStateFlow<List<Contributor>>(emptyList())
    val contributors: StateFlow<List<Contributor>> = _contributors

    fun loadRepository(repoId: Long) {
        viewModelScope.launch {
            val repo = repository.getRepositoryDetails(repoId)
            _repository.value = repo
            val contributors = repository.getContributors(repo.name)
            _contributors.value = contributors
        }
    }
}
