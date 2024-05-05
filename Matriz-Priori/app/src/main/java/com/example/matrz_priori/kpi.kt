package com.example.matrz_priori

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class kpi : AppCompatActivity() {

    companion object {
        // Variável global para armazenar o vetor KPIFactorN
        var KPIFactorN: DoubleArray = DoubleArray(16)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_kpi)

        // Variáveis para armazenar as respostas dos CheckBoxes


        val btnProximo: Button = findViewById(R.id.btnProximo)
        val checkBoxIds = listOf(
            R.id.opcao1, R.id.opcao2, R.id.opcao3, R.id.opcao4,
            R.id.opcao5, R.id.opcao6, R.id.opcao7, R.id.opcao8,
            R.id.opcao9, R.id.opcao10, R.id.opcao11, R.id.opcao12,
            R.id.opcao13, R.id.opcao14
        )

        val respostas = IntArray(checkBoxIds.size)

        // Desativar o botão "Próximo" inicialmente
        btnProximo.isEnabled = false
        btnProximo.setBackgroundColor(getColor(R.color.gray_disabled_color))

        // Configuração dos CheckBoxes
        checkBoxIds.forEachIndexed { index, checkBoxId ->
            val checkBox = findViewById<CheckBox>(checkBoxId)
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                // Atualizar contagem de seleções
                val numSelecionados = checkBoxIds.count { findViewById<CheckBox>(it).isChecked }
                // Habilitar botão "Próximo" apenas se 5 CheckBoxes estiverem selecionados
                btnProximo.isEnabled = numSelecionados == 5
                btnProximo.setBackgroundColor(if (btnProximo.isEnabled) getColor(R.color.blue_primary_color) else getColor(R.color.gray_disabled_color))
                if (numSelecionados > 5) {
                    // Exibe um Toast para avisar o usuário
                    Toast.makeText(this, "Escolha 5 opções !", Toast.LENGTH_SHORT).show()
                }
                respostas[index] = if (isChecked) 1 else 0
                // Atualizar KPIFactorN sempre que houver uma mudança
                updateKPIFactorN(respostas)
            }
        }

        btnProximo.setOnClickListener {
            val intent = Intent(this, setor::class.java)
            startActivity(intent)
        }



        // Calcular KPIFactorN inicialmente
        updateKPIFactorN(respostas)
    }

    // Método para calcular e atualizar KPIFactorN com base nas respostas dos CheckBoxes
    private fun updateKPIFactorN(respostas: IntArray) {
        // Matriz 14 por 16 com os valores fornecidos
        val matriz: Array<Array<Int>> = arrayOf(
            arrayOf(3, 1, 0, 3, 1, 3, 3, 1, 3, 3, 1, 3, 1, 1, 1, 1),
            arrayOf(3, 3, 3, 3, 3, 3, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3),
            arrayOf(1, 0, 0, 1, 0, 3, 1, 0, 3, 1, 0, 3, 1, 1, 1, 1),
            arrayOf(3, 3, 0, 3, 3, 0, 1, 1, 0, 3, 3, 0, 1, 1, 1, 1),
            arrayOf(3, 0, 3, 1, 1, 0, 1, 1, 0, 3, 3, 0, 1, 1, 1, 1),
            arrayOf(3, 1, 1, 3, 3, 3, 1, 1, 1, 3, 3, 3, 3, 3, 3, 1),
            arrayOf(3, 1, 3, 3, 1, 1, 1, 1, 1, 3, 1, 1, 3, 1, 3, 1),
            arrayOf(1, 0, 0, 3, 0, 3, 1, 0, 1, 3, 0, 3, 1, 3, 1, 1),
            arrayOf(1, 1, 1, 0, 0, 0, 3, 3, 3, 1, 1, 1, 3, 3, 1, 3),
            arrayOf(1, 3, 0, 0, 3, 0, 1, 3, 0, 1, 3, 0, 1, 1, 1, 1),
            arrayOf(3, 1, 0, 3, 0, 1, 3, 0, 1, 3, 0, 1, 1, 3, 1, 3),
            arrayOf(3, 1, 1, 1, 1, 1, 3, 1, 3, 3, 1, 3, 3, 3, 3, 3),
            arrayOf(0, 0, 3, 0, 3, 0, 0, 3, 0, 0, 3, 0, 1, 3, 1, 3),
            arrayOf(3, 3, 0, 3, 3, 1, 3, 3, 1, 3, 3, 1, 1, 3, 3, 1)
        )

        // Matriz para armazenar os resultados da multiplicação
        val matrizResultante: Array<Array<Int>> = Array(15) { Array(16) { 0 } }

        // Multiplicação dos valores do vetor respostas pelas linhas da matriz
        for (i in respostas.indices) {
            val resposta = respostas[i]
            for (j in matriz[i].indices) {
                matrizResultante[i][j] = resposta * matriz[i][j]
            }
        }

        // Vetor para armazenar os resultados da soma vertical
        val KPIFactor = DoubleArray(16)

        // Soma dos elementos verticalmente da matriz resultante
        for (i in matrizResultante[0].indices) {
            var soma = 0.0
            for (j in matrizResultante.indices) {
                soma += matrizResultante[j][i]
            }
            KPIFactor[i] = soma
        }

        // Variável para armazenar a soma dos elementos do vetor KPIFactor
        val totK = KPIFactor.sum()

        // Calcular os resultados da divisão e armazenar no vetor KPIFactorN
        for (i in KPIFactor.indices) {
            KPIFactorN[i] = KPIFactor[i] / totK
        }

    }
}



