package com.example.birthdaygreet

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)


        button.setOnClickListener {
            val message = findViewById<EditText>(R.id.editText_name).editableText.toString();
            Log.d("error", message)

            val intent = Intent(this, BirthdayGreetingActivity::class.java).apply {
                putExtra(EXTRA_GREET, message)
            }
            startActivity(intent)
        }

    }

    companion object {
        const val EXTRA_GREET = "HAPPY_BIRTHDAY"
    }


}