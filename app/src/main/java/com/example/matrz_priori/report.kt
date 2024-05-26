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

        // Vetor para a primeira coluna
        val textsFirstColumn = arrayOf(
            "Process",
            "Process",
            "Process",
            "Technology",
            "Technology",
            "Technology",
            "Technology",
            "Technology",
            "Technology",
            "Organization",
            "Organization",
            "Organization",
            "Organization",
            "Organization",
            "Organization",
            "Organization"
        )

        // Vetor para a segunda coluna
        val textsSecondColumn = arrayOf(
            "Vertical\nIntegration",
            "Horizontal\nIntegration",
            "Integrated\nProduct\nLifecycle",
            "Shop Floor\nAutomation",
            "Enterprise\nAutomation",
            "Facility\nAutomation",
            "Shop Floor\nConnectivity",
            "Enterprise\nConnectivity",
            "Facility\nConnectivity",
            "Shop Floor\nIntelligence",
            "Enterprise\nIntelligence",
            "Facility\nIntelligence",
            "Workforce\nLearning &\nDevelopment",
            "Leadership\nCompetency",
            "Inter-/Intra-\nCollaboration",
            "Strategy &\nGovernance"
        )

        // Lista de pares (valor da quarta coluna, linha da tabela)
        val rows = mutableListOf<Pair<Double, TableRow>>()

        // Loop para criar as 16 linhas e adicionar à lista de pares
        for (i in 0 until 16) {
            val tableRow = createTableRow(textsFirstColumn[i], textsSecondColumn[i], respostaForms[i], impactValue[i])
            rows.add(Pair(impactValue[i], tableRow))
        }

        // Ordena a lista de pares com base no valor da quarta coluna
        rows.sortByDescending { it.first }

        // Remove todas as linhas da tabela
        tableLayout.removeAllViews()

        // Adiciona as linhas ordenadas à tabela
        rows.forEach { (_, tableRow) -> tableLayout.addView(tableRow) }

        val btnProximo: Button = findViewById(R.id.btnhome)
        btnProximo.setOnClickListener {
            val intent = Intent(this, inicial::class.java)
            startActivity(intent)
        }
    }

    private fun createTableRow(
        textFirstColumn: String,
        textSecondColumn: String,
        resposta: Int,
        impactValue: Double
    ): TableRow {
        val tableRow = TableRow(this)
        val textView1 = createTextView(textFirstColumn)
        val textView2 = createTextView(textSecondColumn)
        val textView3 = createTextView(resposta.toString())
        val textView4 = createTextView(String.format("%.4f", impactValue))
        tableRow.addView(textView1)
        tableRow.addView(textView2)
        tableRow.addView(textView3)
        tableRow.addView(textView4)
        return tableRow
    }

    private fun createTextView(text: String): TextView {
        val textView = TextView(this)
        textView.text = text
        val layoutParams = TableRow.LayoutParams(
            TableRow.LayoutParams.WRAP_CONTENT,
            TableRow.LayoutParams.WRAP_CONTENT,
            1f // Peso para distribuir igualmente a largura das células
        )
        layoutParams.setMargins(2, 2, 2, 2)
        textView.layoutParams = layoutParams
        textView.gravity = Gravity.CENTER
        textView.textSize = 16f
        textView.setPadding(10, 10, 10, 10)
        textView.setTextColor(Color.BLACK)
        textView.setBackgroundResource(R.drawable.table_row_border)
        return textView
    }
}