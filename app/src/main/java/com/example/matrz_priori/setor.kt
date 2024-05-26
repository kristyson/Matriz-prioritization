package com.example.matrz_priori

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioGroup

class setor : AppCompatActivity() {

    companion object {
        // Variável global para armazenar o vetor proximityFactorN
        var proximityFactorN: DoubleArray = DoubleArray(16)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setor)

        // Obtenha o RadioGroup
        val radioGroup = findViewById<RadioGroup>(R.id.answer1Group)


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

        // Obter vetor de respostas da Activity forms
        val respostaForms = forms.respostaForms

        // Configuração dos RadioButtons
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
                val proximityFactor = DoubleArray(16)
                for (j in 0 until 16) {
                    proximityFactor[j] = (matriz[answer][j] - respostaForms[j]).toDouble()
                }

                // Variável para armazenar a soma dos elementos do vetor proximityFactor
                val totS = proximityFactor.sum()

                // Calcular os resultados da divisão e armazenar no vetor proximityFactorN
                for (i in proximityFactor.indices) {
                    proximityFactorN[i] = proximityFactor[i] / totS
                }

            }
        }

        val btnProximo: Button = findViewById(R.id.btnProximo)
        btnProximo.isEnabled = false // Inicialmente desativado
        btnProximo.setBackgroundColor(getColor(R.color.gray_disabled_color))

        // Configuração do listener para o RadioGroup
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            // Verifique se alguma opção foi selecionada
            btnProximo.isEnabled = checkedId != -1
            btnProximo.setBackgroundColor(if (btnProximo.isEnabled) getColor(R.color.blue_primary_color) else getColor(R.color.gray_disabled_color))
        }

        btnProximo.setOnClickListener {
            val intent = Intent(this, horizontal::class.java)
            startActivity(intent)
        }
    }
}
