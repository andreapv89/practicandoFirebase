package com.example.practicandofirebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.practicandofirebase.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtEmail = findViewById<EditText>(R.id.et_login_email)
        val txtPassword = findViewById<EditText>(R.id.ed_login_pass)

        val btnLogin: Button = findViewById(R.id.bt_login_login)

        val db = Firebase.auth
        btnLogin.setOnClickListener{
            var correo:String = txtEmail.text.toString()
            var clave:String = txtPassword.text.toString()

            Toast.makeText(this,"Correo "+correo ,Toast.LENGTH_LONG).show()

            db.signInWithEmailAndPassword(correo,clave)
                .addOnCompleteListener (this){ task->
                    if(task.isSuccessful){
                        Toast.makeText(this,"Autenticacion exitosa",Toast.LENGTH_LONG).show()
                        startActivity(Intent(this,HomeActivity::class.java))
                    }
                    else
                    {
                        Toast.makeText(this,"Correo o clave invalida",Toast.LENGTH_LONG).show()
                    }

                }

        }

    }
}