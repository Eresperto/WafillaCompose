package com.example.wafilla

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.content.ContextCompat

class LKcompanies : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.activity)
        setContentView(R.layout.lkcompanies)
        var btn_settings = findViewById<Button>(R.id.btn_settings)
        var btn_invpj = findViewById<Button>(R.id.btn_investedpj)
        var btn_homemenu = findViewById<Button>(R.id.btn_homemenu)
        var btn_lkmenu = findViewById<Button>(R.id.btn_lkmenu)
        var btn_exitmenu = findViewById<Button>(R.id.btn_exitmenu)
        var btn_apimenu = findViewById<Button>(R.id.btn_apimenu)
        val intent_lkmenu = Intent(this, LKcompanies::class.java)
        val intent_exitmenu = Intent(this, Registration::class.java)
        val intent_homemenu = Intent(this, LKstartup::class.java)
        val intent_settings = Intent(this, Settings::class.java)
        val intent_invpj = Intent(this, LKstartup::class.java)
        val intent_api = Intent(this, API::class.java)


        btn_settings.setOnClickListener {
            startActivity(intent_settings)
        }
        btn_lkmenu.setOnClickListener {
            startActivity(intent_lkmenu)
        }
        btn_exitmenu.setOnClickListener {
            startActivity(intent_exitmenu)
        }
        btn_apimenu.setOnClickListener {
            startActivity(intent_api)
        }

    }
}