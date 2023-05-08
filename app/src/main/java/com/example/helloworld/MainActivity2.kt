package com.example.helloworld

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity2 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)


        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()
        setContentView(R.layout.activity_main2)


        var btnNext0 = findViewById<Button>(R.id.button)
        btnNext0.setOnClickListener{
            val intent = Intent (this,MainActivity2::class.java)
            startActivity(intent)
        }


        var btnNext1 = findViewById<Button>(R.id.button2)
        btnNext1.setOnClickListener{
            val intent = Intent (this,MainActivity2::class.java)
            startActivity(intent)
        }
}}