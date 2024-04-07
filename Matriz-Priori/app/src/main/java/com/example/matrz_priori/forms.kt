package com.example.matrz_priori

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioGroup

class forms : AppCompatActivity() {

    companion object {
        // Variável global para armazenar o vetor respostaForms
        var respostaForms: IntArray = IntArray(16) { -1 }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forms)

        val btnProximo: Button = findViewById(R.id.btnProximo)

        btnProximo.setOnClickListener {

            // Iniciar a próxima activity e enviar o vetor respostaForms como extra na Intent
            val intent = Intent(this, custo::class.java)
            startActivity(intent)
        }

        setupRadioGroups()
    }

    private fun setupRadioGroups() {
        for (i in 1..16) {
            val radioGroup = findViewById<RadioGroup>(resources.getIdentifier("answer${i}Group", "id", packageName))
            radioGroup.setOnCheckedChangeListener { _, checkedId ->
                // Determina o valor selecionado com base no ID do RadioButton
                val respostaSelecionada = when (checkedId) {
                    resources.getIdentifier("answer${i}_0", "id", packageName) -> 0
                    resources.getIdentifier("answer${i}_1", "id", packageName) -> 1
                    resources.getIdentifier("answer${i}_2", "id", packageName) -> 2
                    resources.getIdentifier("answer${i}_3", "id", packageName) -> 3
                    resources.getIdentifier("answer${i}_4", "id", packageName) -> 4
                    resources.getIdentifier("answer${i}_5", "id", packageName) -> 5
                    else -> -1
                }

                // Armazena a resposta selecionada no vetor respostaForms
                if (respostaSelecionada != -1) {
                    respostaForms[i - 1] = respostaSelecionada
                }
            }
        }
    }
}
