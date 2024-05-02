package com.example.matrz_priori

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
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

        val tableLayout: TableLayout = findViewById(R.id.tableLayout)
        val impactValue = horizontal.impactValue
        val respostaForms = forms.respostaForms

        val editTextNomeFantasia = inicial.editTextNomeFantasia
        val editTextCnpj = inicial.editTextCnpj
        val radioGroupPorteEmpresa = inicial.radioGroupPorteEmpresa


        val nomeFantasiaTextView = findViewById<TextView>(R.id.NomeFantasia)
        val nomeFantasia = editTextNomeFantasia.text.toString()
        nomeFantasiaTextView.text = "Nome: $nomeFantasia"

        val cnpjTextView = findViewById<TextView>(R.id.cnpj)
        val cnpj = editTextCnpj.text.toString()
        cnpjTextView.text = "CNPJ: $cnpj"

        val porteEmpresaTextView = findViewById<TextView>(R.id.porte)
        val porteEmpresa = when (radioGroupPorteEmpresa.checkedRadioButtonId) {
            R.id.radioButtonMEI -> "MEI"
            R.id.radioButtonMicroempresa -> "Microempresa"
            R.id.radioButtonPequenaEmpresa -> "Pequena empresa"
            R.id.radioButtonMediaEmpresa -> "Média empresa"
            R.id.radioButtonGrandeEmpresa -> "Grande empresa"
            else -> ""
        }
        porteEmpresaTextView.text = "Porte de empresa: $porteEmpresa"

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


        // Loop para criar as 16 linhas
        for (i in 0 until 16) {
            val tableRow = TableRow(this)
            for (j in 1..4) {7
                val textView = TextView(this)
                if (j == 1) {
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
                layoutParams.setMargins(2, 2, 2, 2)
                textView.layoutParams = layoutParams
                textView.gravity = Gravity.CENTER
                textView.textSize = 16f
                textView.setPadding(10, 10, 10, 10) // Adicionar padding interno

                // Definir a cor do texto para preto
                textView.setTextColor(Color.BLACK)

                // Adicionar bordas às células
                textView.setBackgroundResource(R.drawable.table_row_border)

                tableRow.addView(textView)
            }
            tableLayout.addView(tableRow)
        }

        val btnProximo: Button = findViewById(R.id.btnhome)

        btnProximo.setOnClickListener {
            val intent = Intent(this, inicial::class.java)
            startActivity(intent)
        }
    }
}
