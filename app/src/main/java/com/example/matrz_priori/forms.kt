package com.example.matrz_priori

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class forms : AppCompatActivity() {

    companion object {
        var respostaForms: IntArray = IntArray(16) { -1 }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_forms)

        val btnProximo: Button = findViewById(R.id.btnProximo)
        btnProximo.isEnabled = false
        btnProximo.setBackgroundColor(getColor(R.color.gray_disabled_color))

        btnProximo.setOnClickListener {
            if (respostaForms.all { it != -1 }) {
                val intent = Intent(this, custo::class.java)
                startActivity(intent)
            }
        }

        setupRadioGroups(btnProximo)
    }

    private fun setupRadioGroups(btnProximo: Button) {
        for (i in 1..16) {
            val radioGroup = findViewById<RadioGroup>(resources.getIdentifier("answer${i}Group", "id", packageName))
            radioGroup.setOnCheckedChangeListener { _, checkedId ->
                val respostaSelecionada = when (checkedId) {
                    resources.getIdentifier("answer${i}_0", "id", packageName) -> 0
                    resources.getIdentifier("answer${i}_1", "id", packageName) -> 1
                    resources.getIdentifier("answer${i}_2", "id", packageName) -> 2
                    resources.getIdentifier("answer${i}_3", "id", packageName) -> 3
                    resources.getIdentifier("answer${i}_4", "id", packageName) -> 4
                    resources.getIdentifier("answer${i}_5", "id", packageName) -> 5
                    else -> -1
                }

                if (respostaSelecionada != -1) {
                    respostaForms[i - 1] = respostaSelecionada
                }

                btnProximo.isEnabled = respostaForms.all { it != -1 }
                if (btnProximo.isEnabled) {
                    btnProximo.setBackgroundColor(getColor(R.color.blue_primary_color))
                } else {
                    btnProximo.setBackgroundColor(getColor(R.color.gray_disabled_color))
                }
            }
        }
    }
}
