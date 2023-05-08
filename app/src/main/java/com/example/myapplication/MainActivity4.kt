package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        setContentView(R.layout.activity_main4)

        var buttonNext0 = findViewById<Button>(R.id.button6)
        buttonNext0.setOnClickListener {
            val intent = Intent(this, MainActivity10::class.java)
            startActivity(intent)

            var buttonNext1 = findViewById<Button>(R.id.button8)
            buttonNext1.setOnClickListener {
                val intent = Intent(this, MainActivity2::class.java)
                startActivity(intent)
            }
        }
    }
}