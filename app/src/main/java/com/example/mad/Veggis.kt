package com.example.mad

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Veggis : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()
        setContentView(R.layout.activity_veggis)
    }
}