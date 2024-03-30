package com.example.matrz_priori

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class setor : AppCompatActivity() {

    companion object {
        // Variável global para armazenar o vetor costFactorN
        var proximityFactorN: DoubleArray = DoubleArray(16)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_setor)

        val btnProximo: Button = findViewById(R.id.btnProximo)

        btnProximo.setOnClickListener{
            val intent = Intent(this, horizontal::class.java)
            startActivity(intent)
        }

        // Matriz 14 por 16 com os valores fornecidos
        val matriz: Array<Array<Int>> = arrayOf(
            arrayOf(3, 3, 3, 2, 3, 2, 4, 4, 2, 3, 3, 2, 3, 3, 3, 3),
            arrayOf(3, 4, 4, 2, 3, 2, 4, 4, 2, 3, 3, 2, 4, 4, 4, 4),
            arrayOf(4, 4, 3, 3, 3, 2, 4, 4, 3, 5, 4, 4, 4, 4, 4, 4),
            arrayOf(4, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4),
            arrayOf(4, 3, 3, 3, 3, 3, 4, 4, 4, 4, 3, 2, 4, 4, 4, 4),
            arrayOf(4, 4, 4, 3, 3, 2, 4, 4, 4, 2, 3, 2, 3, 3, 3, 3),
            arrayOf(2, 4, 3, 3, 4, 3, 4, 4, 4, 2, 3, 2, 3, 3, 3, 3),
            arrayOf(3, 4, 4, 2, 2, 2, 4, 4, 2, 2, 3, 2, 3, 3, 4, 3),
            arrayOf(4, 4, 3, 4, 3, 2, 4, 4, 2, 3, 2, 3, 2, 4, 4, 4),
            arrayOf(2, 3, 3, 2, 2, 2, 2, 3, 2, 3, 2, 2, 2, 2, 2, 2),
            arrayOf(4, 3, 3, 3, 2, 3, 4, 4, 4, 4, 4, 3, 4, 4, 4, 4),
            arrayOf(3, 3, 3, 3, 3, 3, 4, 4, 4, 2, 2, 2, 2, 3, 3, 3),
            arrayOf(4, 4, 3, 3, 3, 3, 4, 4, 4, 4, 3, 3, 2, 4, 4, 4),
            arrayOf(3, 4, 4, 2, 3, 2, 4, 4, 2, 3, 3, 2, 3, 4, 4, 4)
        )

        // Variável para armazenar a resposta do setor
        var answer = -1

        // Obtém o vetor de respostas da tela forms
        val respostasForms = intent.getIntArrayExtra("respostas") ?: IntArray(16) { -1 }

        // Vetor para armazenar o "Proximity Factor"
        val proximityFactor = DoubleArray(16)

// Configuração dos RadioButtons
        val radioGroup = findViewById<RadioGroup>(R.id.answer1Group)
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            answer = when (checkedId) {
                R.id.setor1 -> 0
                R.id.setor2 -> 1
                R.id.setor3 -> 2
                R.id.setor4 -> 3
                R.id.setor5 -> 4
                R.id.setor6 -> 5
                R.id.setor7 -> 6
                R.id.setor8 -> 7
                R.id.setor9 -> 8
                R.id.setor10 -> 9
                R.id.setor11 -> 10
                R.id.setor12 -> 11
                R.id.setor13 -> 12
                R.id.setor14 -> 13
                else -> -1
            }

            // Verifica se a resposta do setor é válida
            if (answer != -1) {
                // Calcula o "Proximity Factor" para cada linha da matriz
                for (i in matriz.indices) {
                    proximityFactor[i] = (matriz[i][answer] - respostasForms[i]).toDouble()
                }
            }

            // Variável para armazenar a soma dos elementos do vetor proximityFactor
            var TotS = 0.0

            // Calcular a soma dos elementos do vetor proximityFactor
            for (element in proximityFactor) {
                TotS += element
            }

            // Calcular os resultados da divisão e armazenar no vetor
            for (i in proximityFactor.indices) {
                proximityFactorN[i] = proximityFactor[i] / TotS
            }

        }

    }

}

