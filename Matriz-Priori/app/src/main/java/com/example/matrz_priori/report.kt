package com.example.matrz_priori

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class report : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_report)

        val impactValue = horizontal.impactValue
        val tableLayout: TableLayout = findViewById(R.id.tableLayout)
        val respostaForms = forms.respostaForms

        // Array de strings para os textos da segunda coluna
        val texts = arrayOf(
            "Vertical\nIntegration",
            "Horizontal\nIntegration",
            "Integrated\n" +
                    "Product\n" +
                    "Lifecycle",
            "Shop Floor\n" +
                    "Automation",
            "Enterprise\n" +
                    "Automation",
            "Facility\n" +
                    "Automation",
            "Shop Floor\n" +
                    "Connectivity",
            "Enterprise\n" +
                    "Connectivity",
            "Facility\n" +
                    "Connectivity",
            "Shop Floor\n" +
                    "Intelligence",
            "Enterprise\n" +
                    "Intelligence",
            "Facility\n" +
                    "Intelligence",
            "Workforce\n" +
                    "Learning &\n" +
                    "Development",
            "Leadership\n" +
                    "Competency",
            "Inter-/Intra-\n" +
                    "Collaboration",
            "Strategy &\n" +
                    "Governance"
        )

        val blueColors = arrayOf(
            Color.parseColor("#215483"),
            Color.parseColor("#246FB3"),
            Color.parseColor("#2CA2BC")
        )

        // Loop para criar as 16 linhas
        for (i in 0 until 16) {
            val tableRow = TableRow(this)
            for (j in 1..4) {
                val textView = TextView(this)
                if (j == 1) {
                    textView.setBackgroundColor(
                        when {
                            i < 3 -> blueColors[0] // Primeiras 3 linhas
                            i < 12 -> blueColors[1] // Próximas 9 linhas
                            else -> blueColors[2] // Últimas 4 linhas
                        }
                    )
                    textView.text = when {
                        i < 3 -> "Process"
                        i < 12 -> "Technology"
                        else -> "Organization"
                    }
                } else if (j == 2) {
                    textView.text = texts[i]
                } else if (j == 3) {
                    textView.text = respostaForms[i].toString() // Definir o valor do vetor respostaForms
                } else if (j == 4) {
                    textView.text = String.format("%.4f", impactValue[i])
                    textView.textSize = 14f
                }

                // Definir parâmetros de layout para os TextViews
                val layoutParams = TableRow.LayoutParams(
                    TableRow.LayoutParams.WRAP_CONTENT,
                    TableRow.LayoutParams.WRAP_CONTENT
                )
                layoutParams.setMargins(4, 4, 4, 4)
                textView.layoutParams = layoutParams
                textView.gravity = Gravity.CENTER
                textView.textSize = 12f

                tableRow.addView(textView)
            }
            tableLayout.addView(tableRow)
        }

        val btnProximo: Button = findViewById(R.id.btnhome)

        btnProximo.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
