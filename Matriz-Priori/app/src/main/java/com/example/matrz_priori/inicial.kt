package com.example.matrz_priori

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class inicial : AppCompatActivity() {

    companion object {
        lateinit var editTextNomeFantasia: EditText
        lateinit var editTextCnpj: EditText
        lateinit var radioGroupPorteEmpresa: RadioGroup
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicial)

        // Inicialização dos elementos da interface
        editTextNomeFantasia = findViewById(R.id.editTextNomeFantasia)
        editTextCnpj = findViewById(R.id.editTextCnpj)
        radioGroupPorteEmpresa = findViewById(R.id.radioGroupPorteEmpresa)

        // Configura o botão para salvar os dados da empresa
        val buttonSalvar = findViewById<Button>(R.id.buttonEnviar)
        buttonSalvar.setOnClickListener {
            salvarDadosEmpresa()
            Handler().postDelayed({
            val intent = Intent(this, forms::class.java)
            startActivity(intent)
            }, 2000)
        }

        // Carrega os dados da empresa caso já tenham sido salvos
        carregarDadosEmpresa()
    }

    // Função para salvar os dados da empresa utilizando SharedPreferences
    private fun salvarDadosEmpresa() {
        val nomeFantasia = editTextNomeFantasia.text.toString()
        val cnpj = editTextCnpj.text.toString()
        val porteEmpresa = obterPorteEmpresa()

        // Salva os dados da empresa utilizando SharedPreferences
        val sharedPreferences = getSharedPreferences("dados_empresa", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("nomeFantasia", nomeFantasia)
        editor.putString("cnpj", cnpj)
        editor.putString("porteEmpresa", porteEmpresa)
        editor.apply()

        Toast.makeText(this, "Dados da empresa salvos com sucesso!", Toast.LENGTH_SHORT).show()
    }

    // Função para carregar os dados da empresa salvos anteriormente
    private fun carregarDadosEmpresa() {
        val sharedPreferences = getSharedPreferences("dados_empresa", Context.MODE_PRIVATE)
        val nomeFantasiaSalvo = sharedPreferences.getString("nomeFantasia", "")
        val cnpjSalvo = sharedPreferences.getString("cnpj", "")
        val porteEmpresaSalvo = sharedPreferences.getString("porteEmpresa", "")

        // Atualiza os campos da interface com os dados carregados
        editTextNomeFantasia.setText(nomeFantasiaSalvo)
        editTextCnpj.setText(cnpjSalvo)
        // Aqui você pode exibir ou utilizar o porteEmpresaSalvo conforme necessário
    }

    // Função para obter o porte da empresa com base no RadioButton selecionado
    private fun obterPorteEmpresa(): String {
        return when (radioGroupPorteEmpresa.checkedRadioButtonId) {
            R.id.radioButtonMEI -> "MEI - Até R$ 81.000,00"
            R.id.radioButtonMicroempresa -> "Microempresa - Até R$ 360.000,00"
            R.id.radioButtonPequenaEmpresa -> "Pequena empresa - De R$ 360.000,01 a R$ 4.800.000,00"
            R.id.radioButtonMediaEmpresa -> "Média empresa - De R$ 4.800.000,01 a R$ 300.000.000,00"
            R.id.radioButtonGrandeEmpresa -> "Grande empresa - Acima de R$ 300.000.000,00"
            else -> ""
        }
    }
}
