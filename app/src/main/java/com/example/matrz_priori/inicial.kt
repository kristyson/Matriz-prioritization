package com.example.matrz_priori
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
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
        lateinit var buttonEnviar: Button
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicial)

        // Inicialização dos elementos da interface
        editTextNomeFantasia = findViewById(R.id.editTextNomeFantasia)
        editTextCnpj = findViewById(R.id.editTextCnpj)
        radioGroupPorteEmpresa = findViewById(R.id.radioGroupPorteEmpresa)
        buttonEnviar = findViewById(R.id.buttonEnviar)

        // Configura o botão para ficar indisponível inicialmente
        buttonEnviar.isEnabled = false
        buttonEnviar.setBackgroundTintList(getColorStateList(R.color.gray_disabled_color))

        // Verifica se todos os campos estão preenchidos para habilitar o botão
        editTextNomeFantasia.addTextChangedListener(textWatcher)
        editTextCnpj.addTextChangedListener(textWatcher)
        radioGroupPorteEmpresa.setOnCheckedChangeListener { _, _ -> verificarCamposPreenchidos() }

        // Configura o clique do botão
        buttonEnviar.setOnClickListener {
            // Exibe um Toast por 2 segundos
            Toast.makeText(this, "Dados enviados com sucesso!", Toast.LENGTH_SHORT).show()
            Handler().postDelayed({
                val intent = Intent(this, forms::class.java)
                startActivity(intent)
            }, 2000)
        }
    }

    // Listener para verificar se todos os campos estão preenchidos
    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            verificarCamposPreenchidos()
        }

        override fun afterTextChanged(s: Editable?) {}
    }

    // Função para verificar se todos os campos estão preenchidos e habilitar/desabilitar o botão
    private fun verificarCamposPreenchidos() {
        val nomeFantasia = editTextNomeFantasia.text.toString().trim()
        val cnpj = editTextCnpj.text.toString().trim()
        val porteEmpresa = obterPorteEmpresa()

        val todosCamposPreenchidos = nomeFantasia.isNotEmpty() && cnpj.isNotEmpty() && porteEmpresa.isNotEmpty()

        buttonEnviar.isEnabled = todosCamposPreenchidos
        buttonEnviar.setBackgroundTintList(getColorStateList(if (todosCamposPreenchidos) R.color.blue_primary_color else R.color.gray_disabled_color))
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

    override fun onDestroy() {
        super.onDestroy()
        limparDadosEmpresa()
    }

    // Função para limpar os dados da empresa nos SharedPreferences
    private fun limparDadosEmpresa() {
        val sharedPreferences = getSharedPreferences("dados_empresa", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear() // Limpa todos os dados
        editor.apply()
    }
}
