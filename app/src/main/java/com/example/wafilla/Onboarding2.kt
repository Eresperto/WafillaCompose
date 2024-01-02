package com.example.wafilla

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Onboarding2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.onboarding2)
        setContentView(R.layout.onboarding1)
        var btn_next = findViewById<Button>(R.id.btn_next)
        var btn_skip = findViewById<Button>(R.id.btn_skip)
        val intent_reg = Intent(this, Registration::class.java)
        val intent_board2 = Intent(this, Onboarding2::class.java)



        btn_next.setOnClickListener {
            startActivity(intent_board2)
        }
        btn_skip.setOnClickListener {
            startActivity(intent_reg)
        }
    }
}