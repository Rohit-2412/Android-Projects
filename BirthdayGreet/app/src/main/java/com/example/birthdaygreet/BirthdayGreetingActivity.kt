package com.example.birthdaygreet

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.birthdaygreet.MainActivity.Companion.EXTRA_GREET

class BirthdayGreetingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_birthday_greeting)

        // Get the Intent that started this activity and extract the string
        val message = intent.getStringExtra(EXTRA_GREET)

        findViewById<TextView>(R.id.textView_greet).apply { text = "Happy Birthday $message!" }
    }
}