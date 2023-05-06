package com.example.mad

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class DeleteUi : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()
        setContentView(R.layout.activity_delete_ui)

        var btnNextTo = findViewById<Button>(R.id.button6)
        btnNextTo.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        var btnNextTo1 = findViewById<Button>(R.id.button7)
        btnNextTo1.setOnClickListener{
            val intent = Intent(this, Veggis::class.java)
            startActivity(intent)
        }

        var btnNextTo2 = findViewById<Button>(R.id.button9)
        btnNextTo2.setOnClickListener{
            val intent = Intent(this, Home::class.java)
            startActivity(intent)
        }
    }
}