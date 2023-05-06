package com.example.mad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class Veggis : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()
        setContentView(R.layout.activity_veggis)

        var btnNextTo = findViewById<Button>(R.id.button)
        btnNextTo.setOnClickListener{
            val intent = Intent(this, UpdateUI::class.java)
            startActivity(intent)
        }

        var btnNextTo1 = findViewById<Button>(R.id.button8)
        btnNextTo1.setOnClickListener{
            val intent1 = Intent(this, DeleteUi::class.java)
            startActivity(intent1)
        }
    }
}