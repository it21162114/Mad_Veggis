package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity7 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main7)

        var buttonNext = findViewById<Button>(R.id.button15)
        buttonNext.setOnClickListener {
            val intent = Intent(this, MainActivity8::class.java)
            startActivity(intent)

            var buttonNext0 = findViewById<Button>(R.id.button16)
            buttonNext0.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}