package com.example.ifunny

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadJoke()

        // function to load the next meme
        findViewById<Button>(R.id.btnNext).setOnClickListener {
            loadJoke()
        }

        // function to share the meme with a message and url to that meme
        findViewById<Button>(R.id.btnShare).setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(
                Intent.EXTRA_TEXT,
                "Hey, Checkout this joke- \n$joke"
            )

            val choice = Intent.createChooser(intent, "Share with")
            startActivity(choice)
        }
    }

    private var joke: String? = null

    private val url =
        "https://v2.jokeapi.dev/joke/Programming,Miscellaneous,Pun?type=single"

    private fun loadJoke() {
        findViewById<ProgressBar>(R.id.pbLoading).visibility = View.VISIBLE
        val queue = Volley.newRequestQueue(this)

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null, { response ->
                findViewById<ProgressBar>(R.id.pbLoading).visibility = View.GONE
                joke = response.getString("joke")
                findViewById<TextView>(R.id.tvJoke).apply {
                    text = joke
                }
            },
            { _ ->
                Toast.makeText(this, "Something went wrong!", Toast.LENGTH_SHORT).show()
            }
        )
        queue.add(jsonObjectRequest)
    }

}