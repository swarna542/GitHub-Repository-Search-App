package com.example.githubreposearch.ui

    import android.webkit.WebView
    import android.webkit.WebViewClient
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.runtime.Composable
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.viewinterop.AndroidView

    @Composable
    fun WebViewScreen(url: String) {
        AndroidView(factory = { context ->
            WebView(context).apply {
                webViewClient = WebViewClient()
                loadUrl(url)
            }
        }, modifier = Modifier.fillMaxSize())
    }
