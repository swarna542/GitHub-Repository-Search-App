package com.example.githubreposearch.viewmodel

import androidx.lifecycle.*
import com.example.githubreposearch.api.Repository
import com.example.githubreposearch.repository.GitHubRepository
import kotlinx.coroutines.launch

class GitHubViewModel(private val repository: GitHubRepository) : ViewModel() {
    private val _repositories = MutableLiveData<List<Repository>>()
    val repositories: LiveData<List<Repository>> get() = _repositories

    private val _selectedRepository = MutableLiveData<Repository>()
    val selectedRepository: LiveData<Repository> get() = _selectedRepository

    fun searchRepositories(query: String, perPage: Int, page: Int) {
        viewModelScope.launch {
            val result = repository.searchRepositories(query)
            _repositories.postValue(result.items)
        }
    }

    fun selectRepository(repository: Repository) {
        _selectedRepository.value = repository
    }
}

class GitHubViewModelFactory(private val repository: GitHubRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GitHubViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GitHubViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
