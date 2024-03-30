package com.example.matrz_priori

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity


class kpi : AppCompatActivity() {

    companion object {
        // Variável global para armazenar o vetor costFactorN
        var KPIFactorN: DoubleArray = DoubleArray(16)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_kpi)


        val btnProximo: Button = findViewById(R.id.btnProximo)

        btnProximo.setOnClickListener{
            val intent = Intent(this, setor::class.java)
            startActivity(intent)
        }


// Matriz 14 por 16 com os valores fornecidos
        val matriz: Array<Array<Int>> = arrayOf(
            arrayOf( 3, 1, 0, 3, 1, 3, 3, 1, 3, 3, 1, 3, 1, 1, 1, 1),
            arrayOf( 3, 3, 3, 3, 3, 3, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3),
            arrayOf( 1, 0, 0, 1, 0, 3, 1, 0, 3, 1, 0, 3, 1, 1, 1, 1),
            arrayOf( 3, 3, 0, 3, 3, 0, 1, 1, 0, 3, 3, 0, 1, 1, 1, 1),
            arrayOf( 3, 0, 3, 1, 1, 0, 1, 1, 0, 3, 3, 0, 1, 1, 1, 1),
            arrayOf( 3, 1, 1, 3, 3, 3, 1, 1, 1, 3, 3, 3, 3, 3, 3, 1),
            arrayOf( 3, 1, 3, 3, 1, 1, 1, 1, 1, 3, 1, 1, 3, 1, 3, 1),
            arrayOf( 1, 0, 0, 3, 0, 3, 1, 0, 1, 3, 0, 3, 1, 3, 1, 1),
            arrayOf( 1, 1, 1, 0, 0, 0, 3, 3, 3, 1, 1, 1, 3, 3, 1, 3),
            arrayOf( 1, 3, 0, 0, 3, 0, 1, 3, 0, 1, 3, 0, 1, 1, 1, 1),
            arrayOf( 3, 1, 0, 3, 0, 1, 3, 0, 1, 3, 0, 1, 1, 3, 1, 3),
            arrayOf( 3, 1, 1, 1, 1, 1, 3, 1, 3, 3, 1, 3, 3, 3, 3, 3),
            arrayOf( 0, 0, 3, 0, 3, 0, 0, 3, 0, 0, 3, 0, 1, 3, 1, 3),
            arrayOf( 3, 3, 0, 3, 3, 1, 3, 3, 1, 3, 3, 1, 1, 3, 3, 1)

        )

// Lista de IDs dos CheckBoxes
        val checkBoxIds = listOf(
            R.id.opcao1, R.id.opcao2, R.id.opcao3, R.id.opcao4,
            R.id.opcao5, R.id.opcao6, R.id.opcao7, R.id.opcao8,
            R.id.opcao9, R.id.opcao10, R.id.opcao11, R.id.opcao12,
            R.id.opcao13, R.id.opcao14
        )

// Variáveis para armazenar as respostas dos CheckBoxes
        val respostas = IntArray(checkBoxIds.size)

// Configuração dos CheckBoxes
        checkBoxIds.forEachIndexed { index, checkBoxId ->
            findViewById<CheckBox>(checkBoxId).setOnCheckedChangeListener { _, isChecked ->
                respostas[index] = if (isChecked) 1 else 0
            }
        }


// Matriz para armazenar os resultados da multiplicação
        val matrizResultante: Array<Array<Int>> = Array(15) { Array(16) { 0 } }

// Multiplicação dos valores do vetor respostas pelas linhas da matriz
        for (i in 0 until respostas.size) {
            val resposta = respostas[i]
            for (j in 0 until matriz[i].size) {
                matrizResultante[i][j] = resposta * matriz[i][j]
            }
        }

// Vetor para armazenar os resultados da soma vertical
        val KPIFactor = DoubleArray(16)

// Soma dos elementos verticalmente da matriz resultante
        for (i in 0 until matrizResultante[0].size) {
            var soma = 0.0  // Use um Double para a soma
            for (j in 0 until matrizResultante.size) {
                soma += matrizResultante[j][i]
            }
            KPIFactor[i] = soma
        }

        // Variável para armazenar a soma dos elementos do vetor KPIFactor
        var TotK = 0.0

        // Calcular a soma dos elementos do vetor KPIFactor
        for (element in KPIFactor) {
            TotK += element
        }


        // Calcular os resultados da divisão e armazenar no vetor
        for (i in KPIFactor.indices) {
            KPIFactorN[i] = KPIFactor[i] / TotK
        }

    }

}


