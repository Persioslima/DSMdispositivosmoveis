package com.example.consultacep.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.consultacep.R
import com.exemplo.consultacep.model.Endereco
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import java.io.Serializable

class MainActivity : AppCompatActivity() {
    private lateinit var editTextCep: EditText
    private lateinit var buttonBuscar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextCep = findViewById(R.id.editTextCep)
        buttonBuscar = findViewById(R.id.buttonBuscar)

        buttonBuscar.setOnClickListener {
            val cep = editTextCep.text.toString()
            buscarCep(cep)
        }
    }

    private fun buscarCep(cep: String) {
        val url = "https://viacep.com.br/ws/$cep/json/"
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                if (body != null) {
                    val endereco = parseJsonToEndereco(body)

                    val intent = Intent(this@MainActivity, ResultadoActivity::class.java)
                    intent.putExtra("endereco", endereco as Serializable)
                    startActivity(intent)
                }
            }
        })
    }

    private fun parseJsonToEndereco(json: String): Endereco {
        val gson = Gson()
        return gson.fromJson(json, Endereco::class.java)
    }
}
