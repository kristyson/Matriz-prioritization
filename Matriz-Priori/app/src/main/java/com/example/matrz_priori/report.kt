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
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class report : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_report)


        val tableLayout: TableLayout = findViewById(R.id.tableLayout)

        // Array de strings para os textos da segunda coluna
        val texts = arrayOf(
            "Vertical\nIntegration",
            "Horizontal\nIntegration",
            "Integrated\n" +
                    "Product\n" +
                    "Lifecycle",
            "Shop Floor\n" +
                    "Automation",
            "Enterpri se\n" +
                    "Automation",
            "Faci lity\n" +
                    "Automation",
            "Shop Floor\n" +
                    "Connectivity",
            "Enterprise\n" +
                    "Connectivity",
            "Facility\n" +
                    "Connectivity",
            "Shop Floor\n" +
                    "Intell igenceQ",
            "Enterprise\n" +
                    "Intelligence",
            "Facility\n" +
                    "Intelligence",
            "Workforce\n" +
                    "Leaming &\n" +
                    "Development",
            "Leadership\n" +
                    "Competency",
            "Inter-/lntra-\n" +
                    "Collaboration",
            "Strategy &\n" +
                    "Govgnance"
            // Adicione os textos restantes conforme necessário
        )

        val blueColors = arrayOf(
            Color.parseColor("#215483"),
            Color.parseColor("#246FB3"),
            Color.parseColor("#2CA2BC")
        )

// Loop para criar as 16 linhas
        for (i in 1..16) {
            val tableRow = TableRow(this)
            for (j in 1..4) {
                val textView = TextView(this)
                if (j == 1) {
                    textView.setBackgroundColor(
                        when {
                            i <= 3 -> blueColors[0] // Primeiras 3 linhas
                            i <= 12 -> blueColors[1] // Próximas 9 linhas
                            else -> blueColors[2] // Últimas 4 linhas
                        }
                    )
                    textView.text = when {
                        i <= 3 -> "Process"
                        i <= 12 -> "Technology"
                        else -> "Organisation"
                    }
                } else if (j == 2) {
                    textView.text = texts[i - 1]
                } else {
                    textView.text = "$i:$j"
                }

                // Defina a largura específica para a primeira célula da primeira coluna
                if (j == 1) {
                    textView.layoutParams = TableRow.LayoutParams(80, TableRow.LayoutParams.WRAP_CONTENT) // Largura específica da célula
                } else if ( j == 2) {
                    textView.layoutParams = TableRow.LayoutParams(100, TableRow.LayoutParams.WRAP_CONTENT)
                } else {
                    textView.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT) // Largura padrão das outras células
                }

                textView.gravity = Gravity.CENTER
                textView.textSize = 12f
                textView.setPadding(8, 8, 8, 8)
                tableRow.addView(textView)

                if ( j > 2){
                    textView.textSize = 14f
                }

            }
            tableLayout.addView(tableRow)
        }

        val btnProximo: Button = findViewById(R.id.btnhome)

        btnProximo.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}