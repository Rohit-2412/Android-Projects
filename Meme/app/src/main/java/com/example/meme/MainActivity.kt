package com.example.meme

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
        loadMeme()

        // function to load the next meme
        findViewById<Button>(R.id.btnNext).setOnClickListener {
            loadMeme()
        }

        // function to share the meme with a message and url to that meme
        findViewById<Button>(R.id.btnShare).setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(
                Intent.EXTRA_TEXT,
                "Hey, Checkout this cool meme I got from Reddit $imgUrl"
            )

            val choice = Intent.createChooser(intent, "Share this meme using...")
            startActivity(choice)
        }
    }

    private var imgUrl: String? = null
    private var title : String? = ""
    private fun loadMeme() {
        findViewById<ProgressBar>(R.id.pbLoading).visibility = View.VISIBLE

        val url = "https://meme-api.com/gimme"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null, { response ->
                imgUrl = response.getString("url")
                title = response.getString("title")

                if (imgUrl.isNullOrBlank()) {
                    loadMeme()
                }

                findViewById<TextView>(R.id.title).text = title
                Glide.with(this).load(imgUrl).listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        findViewById<ProgressBar>(R.id.pbLoading).visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        findViewById<ProgressBar>(R.id.pbLoading).visibility = View.GONE
                        return false
                    }

                }).into(findViewById(R.id.ivMeme))
            },
            { _ ->
                Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show()
            }
        )

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

}