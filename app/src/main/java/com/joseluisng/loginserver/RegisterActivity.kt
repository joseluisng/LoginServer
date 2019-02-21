package com.joseluisng.loginserver

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RegisterActivity : AppCompatActivity() {

    val LOG_TAG: String = "Mensaje: "


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnRegister.setOnClickListener {
            val nombre = etNombreRegister.text.toString()
            val email = etEmailRegister.text.toString()
            val password = etPasswordRegister.text.toString()
            if (isValidEmail(email) && isValidPassword(password) && !nombre.isEmpty()){
                //signUpByEmail(email, password)
                //toast("nombre: $nombre  email:  $email   password:  $password")
                //Log.e("Mensaje: ","nombre: $nombre  email:  $email   password:  $password")
                registrarUser(nombre, email, password)
            }else{
                toast("Por favor llene todos los datos y confirme que el correo es valido, contraseña minuno una letra minuscula")
            }
        }

    }

    /*
    fun registrarUser(nombre: String, email: String, password: String){

        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.0.102:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create<ApiService>(ApiService::class.java)

        var usuario : Usuario? = Usuario(nombre, email, password)

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

    /*
    fun registrarUser(nombre: String, email: String, password: String){

        val queue = Volley.newRequestQueue(this@RegisterActivity)
        val URL: String = "http://localhost:3000/usuario"

        val stringRequest = object : StringRequest(Request.Method.POST, URL, Response.Listener { s ->
            // Your success code here
            toast("Se guardo")
            Log.e(LOG_TAG, "response is: $s")
        }, Response.ErrorListener { e ->
            // Your error code here
            Log.e("error", "error")
            toast("No se pudo guardar")
        }) {
            override fun getParams(): Map<String, String> = mapOf("nombre" to nombre, "email" to email, "password" to password)
        }

        queue.add<String>(stringRequest)

    }
    */


    /*
    fun registrarUser(nombre: String, email: String, password: String){

        val queue = Volley.newRequestQueue(this@RegisterActivity)
        val url: String = "http://192.168.0.102:3000/usuario"


        val map = HashMap<String, String>()

        map.put("nombre", nombre)
        map.put("email", email)
        map.put("password", password)


        val jsonArray = JSONArray()
        //jsonArray.put( true)
        jsonArray.put(map)

        val req = JsonArrayRequest(Request.Method.POST, url, jsonArray,
                Response.Listener {response ->
                    Log.i(LOG_TAG, "response is: $response")
                    Log.e(LOG_TAG, "response is: $response")
                    toast("se Agrego ")
                },
                Response.ErrorListener {error ->
                    error.printStackTrace()
                    toast("no se Agrego ")
                }
        )

        queue.add(req)

    }
    */





   fun registrarUser(nombre: String, email: String, password: String){

        val queue = Volley.newRequestQueue(this@RegisterActivity)
        val url: String = "http://192.168.0.102:3000/usuario"

        val jsonObject = JSONObject()
        jsonObject.put("nombre", nombre)
        jsonObject.put("email", email)
        jsonObject.put("password", password)

        val req = JsonObjectRequest(url, jsonObject,
                Response.Listener {response ->
                    Log.i(LOG_TAG, "response is: $response")
                    Log.e(LOG_TAG, "response is: $response")
                    intent = Intent(this, MainActivity::class.java )
                    toast("se Agrego ")
                    startActivity(intent)
                },
                Response.ErrorListener {error ->
                    error.printStackTrace()
                    toast("no se Agrego ")
                }
        )

        queue.add(req)

    }

}
