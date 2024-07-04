package com.example.githubreposearch.ui


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController)
        }
        composable("repo_details/{repoId}") { backStackEntry ->
            val repoId = backStackEntry.arguments?.getString("repoId")?.toLongOrNull()
            if (repoId != null) {
                RepoDetailsScreen(navController, repoId)
            }
        }
        composable("webview/{url}") { backStackEntry ->
            val url = backStackEntry.arguments?.getString("url")
            if (url != null) {
                WebViewScreen(url)
            }
        }
    }
}
