package com.example.matrz_priori

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class splashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val splashTimeOut: Long = 1500

        Handler().postDelayed({
            startActivity(Intent(this, inicial::class.java))
            finish()
        }, splashTimeOut)

    }
}