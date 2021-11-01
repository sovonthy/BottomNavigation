package com.test.bottomnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class TodoDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_detail)

        val title = intent.extras?.getString("title")
        val content = intent.extras?.getString("content")

        val titleView = findViewById<TextView>(R.id.titleTextView)
        titleView.text = title

        val btnBack = findViewById<Button>(R.id.backButton)
        btnBack.setOnClickListener {
            super.onBackPressed();
        }
    }
}