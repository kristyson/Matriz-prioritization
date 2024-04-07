package com.example.matriz_priorization

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Tempo de exibição da tela de splash (em milissegundos)
        val splashTimeOut: Long = 3000 // 3 segundos

        Handler().postDelayed({
            // Após o tempo definido, inicie a próxima atividade
            startActivity(Intent(this, MainActivity::class.java))
            // Finalize a atividade de splash
            finish()
        }, splashTimeOut)

    }
}