package com.github.yuukis.webviewtester

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.github.yuukis.webviewtester.ui.theme.WebViewTesterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WebViewTesterTheme {
                WebViewScreen("https://www.ugtop.com/spill.shtml")
            }
        }
    }
}

@Composable
fun WebViewScreen(url: String) {
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
                settings.userAgentString = "MyWebViewTester/1.0"
                loadUrl(url)
            }
        },
        modifier = Modifier.fillMaxSize()
    )
}