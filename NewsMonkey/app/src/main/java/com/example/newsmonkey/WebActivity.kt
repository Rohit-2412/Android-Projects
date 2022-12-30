package com.example.newsmonkey

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity

class WebActivity : AppCompatActivity() {

    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)

        val webView = findViewById<WebView>(R.id.webView)

        val intent = intent

        if (intent != null) {

            url = intent.getStringExtra("url_key")

            webView!!.loadUrl(url!!)
        }
    }


    override fun onRestart() {
        super.onRestart()
        finish()
    }
}