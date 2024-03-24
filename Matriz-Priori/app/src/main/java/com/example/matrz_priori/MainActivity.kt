package com.example.matrz_priori

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val splashTimeOut: Long = 1000

        Handler().postDelayed({
            startActivity(Intent(this, forms::class.java))
            finish()
        }, splashTimeOut)

    }
}