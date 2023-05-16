package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        setContentView(R.layout.activity_main2)

        var buttonNext = findViewById<Button>(R.id.button2)
        buttonNext.setOnClickListener {
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)

            var buttonNext0 = findViewById<Button>(R.id.button3)
            buttonNext0.setOnClickListener {
                val intent = Intent(this, MainActivity4::class.java)
                startActivity(intent)


                var buttonNext1 = findViewById<Button>(R.id.button19)
                buttonNext1.setOnClickListener {
                    val intent = Intent(this, MainActivity5::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}