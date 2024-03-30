package com.example.matrz_priori

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class custo : AppCompatActivity() {

    companion object {
        // Variável global para armazenar o vetor costFactorN
        var costFactorN: DoubleArray = DoubleArray(16)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_custo)

        val btnProximo: Button = findViewById(R.id.btnProximo)

        btnProximo.setOnClickListener{
            val intent = Intent(this, kpi::class.java)
            startActivity(intent)
        }



        // Declare um array para armazenar as porcentagens
        val percentages = DoubleArray(10)

        // Declare um array para armazenar os IDs dos EditTexts
        val editTextIds = intArrayOf(
            R.id.porcentage1, R.id.porcentage2, R.id.porcentage3, R.id.porcentage4, R.id.porcentage5,
            R.id.porcentage6, R.id.porcentage7, R.id.porcentage8, R.id.porcentage9, R.id.porcentage10
        )

        // Defina os listeners para os EditTexts usando um loop
        for (i in 0 until editTextIds.size) {
            val editText = findViewById<EditText>(editTextIds[i])
            editText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    s?.toString()?.let {
                        percentages[i] = if (it.isNotEmpty()) it.toDouble() / 100 else 0.0
                    }
                }
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })
        }

        // Matriz 10 por 16 com os valores fornecidos
        val matriz: Array<Array<Int>> = arrayOf(
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

        // Matriz para armazenar os resultados da multiplicação
        val matrizResultante: Array<Array<Double>> = Array(10) { Array(16) { 0.0 } }

        // Multiplicação das linhas da matriz original pelos valores das porcentagens
        for (i in matriz.indices) {
            for (j in matriz[i].indices) {
                matrizResultante[i][j] = matriz[i][j] * percentages[i]
            }
        }

        // Vetor para armazenar os resultados da soma vertical
        val costFactor = DoubleArray(16)

        // Soma vertical dos elementos da matriz resultante
        for (j in matrizResultante[0].indices) { // Itera sobre as colunas
            var sum = 0.0
            for (i in matrizResultante.indices) { // Itera sobre as linhas
                sum += matrizResultante[i][j]
            }
            costFactor[j] = sum
        }

        // Variável para armazenar a soma dos elementos do vetor costFactor
        var TotC = 0.0

        // Calcular a soma dos elementos do vetor costFactor
        for (element in costFactor) {
            TotC += element
        }

        // Calcular os resultados da divisão e armazenar no vetor
        for (i in costFactor.indices) {
            costFactorN[i] = costFactor[i] / TotC
        }
        Log.d("MatrizResultante", matrizResultante.contentToString())
    }


}
