package com.example.matrz_priori

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

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
        disableNextButton(btnProximo) // Inicialmente desativado

        // Configuração dos RadioButtons
        val radioGroup = findViewById<RadioGroup>(R.id.answer1Group)
        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.op1 -> {
                    enableNextButton(btnProximo)
                }
                R.id.op2 -> {
                    enableNextButton(btnProximo)
                }
                R.id.op3 -> {
                    enableNextButton(btnProximo)
                }
                else -> {
                    disableNextButton(btnProximo)
                }
            }
        }

        btnProximo.setOnClickListener{
            val intent = Intent(this, report::class.java)
            startActivity(intent)
        }

        // Calcular e exibir o vetor impactValue inicialmente
        calculateImpactValue(costFactorN, KPIFactorN, proximityFactorN, 0.3, 0.4, 0.3)
    }

    private fun enableNextButton(button: Button) {
        button.isEnabled = true
        button.setBackgroundColor(getColor(R.color.blue_primary_color))
    }

    private fun disableNextButton(button: Button) {
        button.isEnabled = false
        button.setBackgroundColor(getColor(R.color.gray_disabled_color))
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
    }
}
