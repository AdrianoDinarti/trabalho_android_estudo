package com.example.appalcoolougasolina

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var textInputAlcool: TextInputLayout
    private lateinit var textAlcool: TextInputEditText

    private lateinit var textInputGasolina: TextInputLayout
    private lateinit var textGasolina: TextInputEditText

    private lateinit var btnCalcular: Button
    private lateinit var textResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        inicializarComponentesInterface()

        btnCalcular.setOnClickListener{
            calcularMelhorPreco()
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun calcularMelhorPreco() {
        val precoAlcool = textAlcool.text.toString()
        val precoGasoliza = textGasolina.text.toString()

        val resultadoValidacao = validarCampos(precoAlcool, precoGasoliza)
        if (resultadoValidacao){

            val precoAlcoolNumero = precoAlcool.toDouble()
            val precoGasolinaNumero = precoGasoliza.toDouble()
            val resultado = precoAlcoolNumero / precoGasolinaNumero

            if(resultado >= 0.7){
                textResultado.text = "É melhor utilizar gasolina"

            }
            else{
                textResultado.text = "É melhor utilizar álcool"
            }

        }
    }

    private fun validarCampos(pAlcool: String, pGasoliza: String): Boolean {

        textInputAlcool.error = null
        textInputGasolina.error = null


        if(pAlcool.isEmpty()){
            textInputAlcool.error = "Digite o preço do álcool!"
            return false
        }
        else if(pGasoliza.isEmpty()){
            textInputGasolina.error = "Digite o preço da gasolina"
            return false
        }

        return true

    }

    private fun inicializarComponentesInterface() {
        textInputAlcool = findViewById(R.id.textInputAlcool)
        textAlcool = findViewById(R.id.textAlcool)

        textInputGasolina = findViewById(R.id.textInputGasolina)
        textGasolina = findViewById(R.id.textGasolina)

        btnCalcular = findViewById(R.id.btnCalcular)
        textResultado = findViewById(R.id.textResultado)
    }
}