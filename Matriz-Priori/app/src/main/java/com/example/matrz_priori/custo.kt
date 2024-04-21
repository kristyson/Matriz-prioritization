package com.example.matrz_priori

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class custo : AppCompatActivity() {

    companion object {
        // Variável global para armazenar o vetor costFactorN
        var costFactorN: DoubleArray = DoubleArray(16)
    }

    // Matriz 10 por 16 com os valores fornecidos
    private val matriz: Array<Array<Int>> = arrayOf(
        arrayOf(0, 1, 3, 0, 3, 0, 0, 1, 0, 0, 3, 0, 1, 1, 1, 1),
        arrayOf(1, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0, 1),
        arrayOf(3, 0, 0, 3, 0, 3, 1, 0, 1, 3, 0, 3, 3, 3, 3, 3),
        arrayOf(3, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 3, 3, 1, 1, 1),
        arrayOf(3, 3, 1, 1, 1, 1, 1, 1, 1, 3, 3, 1, 0, 1, 1, 1),
        arrayOf(1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1),
        arrayOf(1, 1, 3, 0, 3, 0, 0, 1, 0, 0, 3, 0, 3, 1, 3, 3),
        arrayOf(1, 3, 1, 0, 3, 0, 0, 1, 0, 0, 3, 0, 3, 3, 3, 3),
        arrayOf(0, 3, 0, 0, 1, 0, 0, 1, 0, 0, 3, 0, 1, 1, 3, 1),
        arrayOf(1, 1, 0, 1, 1, 3, 1, 1, 1, 1, 1, 3, 0, 1, 0, 1)
    )

    // Array para armazenar as porcentagens
    private val percentages = DoubleArray(10)

    // Array de EditTexts
    private val editTextIds = intArrayOf(
        R.id.porcentage1, R.id.porcentage2, R.id.porcentage3, R.id.porcentage4, R.id.porcentage5,
        R.id.porcentage6, R.id.porcentage7, R.id.porcentage8, R.id.porcentage9, R.id.porcentage10
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_custo)

        val btnProximo: Button = findViewById(R.id.btnProximo)

        // Definir OnClickListener para o botão
        btnProximo.setOnClickListener {
            val intent = Intent(this, kpi::class.java)
            startActivity(intent)
        }

        // Configurar os listeners para os EditTexts
        setupEditTextListeners()

        // Inicializar e atualizar costFactorN
        updateCostFactorN()

        // Atualizar TextView e estado do botão "Próximo"
        updateSumPercentageTextView()
        updateNextButtonState()
    }

    // Método para configurar os listeners para os EditTexts
    private fun setupEditTextListeners() {
        for (i in editTextIds.indices) {
            val editText = findViewById<EditText>(editTextIds[i])
            editText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    s?.toString()?.let {
                        percentages[i] = if (it.isNotEmpty()) it.toDouble() / 100 else 0.0
                        updateCostFactorN()
                        updateSumPercentageTextView()
                        updateNextButtonState()
                    }
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })
        }
    }

    // Método para atualizar costFactorN com os cálculos necessários
    private fun updateCostFactorN() {
        val costFactor = DoubleArray(16)

        // Calcular os elementos do vetor costFactor
        for (j in matriz[0].indices) {
            var sum = 0.0
            for (i in matriz.indices) {
                sum += matriz[i][j] * percentages[i]
            }
            costFactor[j] = sum
        }

        // Calcular a soma dos elementos do vetor costFactor
        val totC = costFactor.sum()

        // Calcular os resultados da divisão e armazenar no vetor costFactorN
        for (i in costFactor.indices) {
            costFactorN[i] = costFactor[i] / totC
        }

    }

    private fun updateSumPercentageTextView() {
        val sumPercentage = percentages.sum() * 100
        val textViewSumPercentage = findViewById<TextView>(R.id.textViewSumPercentage)
        textViewSumPercentage.text = "Soma das Porcentagens: ${sumPercentage.toInt()}%"
    }

    private fun updateNextButtonState() {
        val btnProximo = findViewById<Button>(R.id.btnProximo)
        val sumPercentage = percentages.sum()
        btnProximo.isEnabled = sumPercentage == 1.0 // Habilita o botão se a soma for igual a 100%
    }
}
