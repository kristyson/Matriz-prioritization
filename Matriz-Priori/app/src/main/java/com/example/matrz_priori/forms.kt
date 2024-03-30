package com.example.matrz_priori

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class forms : AppCompatActivity() {

    private val respostas = Array(16) { -1 }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forms)

        val btnProximo: Button = findViewById(R.id.btnProximo)

        btnProximo.setOnClickListener{
            val intent = Intent(this, custo::class.java)
            startActivity(intent)
        }

        setupRadioGroups()
    }

    private fun setupRadioGroups() {
        for (i in 1..16) {
            val radioGroup = findViewById<RadioGroup>(resources.getIdentifier("answer${i}Group", "id", packageName))
            radioGroup.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    resources.getIdentifier("answer${i}_0", "id", packageName) -> respostas[i - 1] = 0
                    resources.getIdentifier("answer${i}_1", "id", packageName) -> respostas[i - 1] = 1
                    resources.getIdentifier("answer${i}_2", "id", packageName) -> respostas[i - 1] = 2
                    resources.getIdentifier("answer${i}_3", "id", packageName) -> respostas[i - 1] = 3
                    resources.getIdentifier("answer${i}_4", "id", packageName) -> respostas[i - 1] = 4
                    resources.getIdentifier("answer${i}_5", "id", packageName) -> respostas[i - 1] = 5
                }
            }
        }
    }

}