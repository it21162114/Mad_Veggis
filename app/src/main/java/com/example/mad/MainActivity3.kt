package com.example.mad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()
        setContentView(R.layout.activity_main3)

        var btnNextTo = findViewById<Button>(R.id.confirm)
        btnNextTo.setOnClickListener{
            val intent = Intent(this, Veggis::class.java)
            startActivity(intent)
        }

    }
}