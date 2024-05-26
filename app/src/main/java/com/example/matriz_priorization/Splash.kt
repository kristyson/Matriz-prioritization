package com.example.matriz_priorization

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val splashTimeOut: Long = 3000

        Handler().postDelayed({
            startActivity(Intent(this, Forms::class.java))
            finish()
        }, splashTimeOut)
    }

}
