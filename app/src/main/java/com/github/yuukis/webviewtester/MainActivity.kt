package com.github.yuukis.webviewtester

import android.os.Bundle
import android.webkit.WebChromeClient
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
                WebViewScreen(
                    url = "https://yuukis.github.io/test_blanktitle4.html",
                    modifier = Modifier.fillMaxSize(),
                    onTitleChanged = { title ->
                        this@MainActivity.title =
                            title.takeUnless { it.isBlank() } ?: getString(R.string.app_name)
                    }
                )
            }
        }
    }
}

@Composable
fun WebViewScreen(
    url: String,
    modifier: Modifier = Modifier,
    onTitleChanged: (String) -> Unit = {}
) {
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                webViewClient = WebViewClient()
                webChromeClient = object : WebChromeClient() {
                    override fun onReceivedTitle(view: WebView?, title: String?) {
                        title?.let(onTitleChanged)
                    }
                }
                settings.javaScriptEnabled = true
//                settings.userAgentString = "MyWebViewTester/1.0"
                loadUrl(url)
            }
        },
        modifier = modifier
    )
}