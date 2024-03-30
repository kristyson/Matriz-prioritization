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

class horizontal : AppCompatActivity() {

    companion object {
        // Variável global para armazenar o vetor impactValue
        var impactValue: DoubleArray = DoubleArray(16)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_horizontal)

        val costFactorN = custo.costFactorN
        val KPIFactorN = kpi.KPIFactorN
        val proximityFactorN = setor.proximityFactorN
        val btnProximo: Button = findViewById(R.id.btnProximo)


        btnProximo.setOnClickListener{
            val intent = Intent(this, report::class.java)
            startActivity(intent)
        }

        // Variáveis para armazenar os pesos
        var wCost = 0.0
        var wKPI = 0.0
        var wProx = 0.0

        // Configuração dos RadioButtons
        val radioGroup = findViewById<RadioGroup>(R.id.answer1Group)
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.op1 -> {
                    wCost = 0.3
                    wKPI = 0.4
                    wProx = 0.3
                }
                R.id.op2 -> {
                    wCost = 0.45
                    wKPI = 0.3
                    wProx = 0.25
                }
                R.id.op3 -> {
                    wCost = 0.6
                    wKPI = 0.2
                    wProx = 0.2
                }
            }

            calculateImpactValue(costFactorN, KPIFactorN, proximityFactorN, wCost, wKPI, wProx)
        }

        // Calcular e exibir o vetor impactValue inicialmente
        calculateImpactValue(costFactorN, KPIFactorN, proximityFactorN, wCost, wKPI, wProx)
    }

    private fun calculateImpactValue(
        costFactorN: DoubleArray,
        KPIFactorN: DoubleArray,
        proximityFactorN: DoubleArray,
        wCost: Double,
        wKPI: Double,
        wProx: Double
    ) {
        // Multiplicação dos vetores pelos pesos
        val weightedCostFactorN = DoubleArray(costFactorN.size)
        val weightedKPIFactorN = DoubleArray(KPIFactorN.size)
        val weightedProximityFactorN = DoubleArray(proximityFactorN.size)

        // Multiplicação dos valores pelos pesos
        for (i in costFactorN.indices) {
            weightedCostFactorN[i] = costFactorN[i] * wCost
        }

        for (i in KPIFactorN.indices) {
            weightedKPIFactorN[i] = KPIFactorN[i] * wKPI
        }

        for (i in proximityFactorN.indices) {
            weightedProximityFactorN[i] = proximityFactorN[i] * wProx
        }

        // Calcular a soma dos vetores ponderados
        for (i in impactValue.indices) {
            impactValue[i] = weightedCostFactorN[i] + weightedKPIFactorN[i] + weightedProximityFactorN[i]
        }

        // Imprimir o vetor impactValue no Logcat
        for ((index, value) in impactValue.withIndex()) {
            Log.d("ImpactValue", "Índice $index: $value")
        }
    }
}

