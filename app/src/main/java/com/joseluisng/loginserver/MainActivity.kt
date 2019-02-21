package com.joseluisng.loginserver

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    val LOG_TAG: String = "Mensaje: "

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        tvRegistro.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }


        btnLogin.setOnClickListener {

            val email = etEmailLogin.text.toString()
            val password = etPasswordLogin.text.toString()

            if (isValidEmail(email) && !password.isEmpty()){

                loginUser(email, password)

            }else{
                toast("Verificar que el correo sea correcto o contraseña no este vacia")
            }

        }


    }


    fun loginUser(email: String, password: String){

        val queue = Volley.newRequestQueue(this)
        val url = "http://192.168.0.102:3000/login"

        val jsonObject = JSONObject()

        jsonObject.put("email", email)
        jsonObject.put("password", password)

        val req = JsonObjectRequest(url, jsonObject,
                Response.Listener { response ->
                    Log.i(LOG_TAG, "response is: $response")
                    Log.e(LOG_TAG, "response is: $response")
                    if(response.getBoolean("ok") == true){
                        intent = Intent(this, SecondActivity::class.java)
                        startActivity(intent)
                    }else{
                        toast("Correo o contraseña incorrecta")
                    }

                },
                Response.ErrorListener { error ->
                    error.printStackTrace()
                    toast("Verifique el correo y la contraseña o su conexión a internet ")
                }
        )

        queue.add(req)

    }


    /*
    fun loginUser(email: String, password: String){
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.0.102:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create<ApiService>(ApiService::class.java)

        var usuario : Usuario? = Usuario(email, password)

        service.registrarUsuario(usuario).enqueue(object : Callback<Usuario> {
            override fun onFailure(call: Call<Usuario>, t: Throwable) {
                t.printStackTrace()
                toast("no se pudo agregar")
            }

            override fun onResponse(call: Call<Usuario>, response: retrofit2.Response<Usuario>) {
                usuario = response.body()
                Log.i("Resultado: ", Gson().toJson(usuario))
                toast("se agrego")
            }

        })
    }
    */

}
