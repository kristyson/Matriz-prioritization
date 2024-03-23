package com.example.matrz_priori

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class custo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_custo)


        val btnProximo: Button = findViewById(R.id.btnProximo)

        btnProximo.setOnClickListener{
            val intent = Intent(this, kpi::class.java)
            startActivity(intent)
        }

    }
}
