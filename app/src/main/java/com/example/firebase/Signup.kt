package com.example.firebase

import android.content.Intent
import android.os.Bundle
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import com.example.firebase.databinding.ActivitySignupBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth


class Signup : AppCompatActivity() {
    private val binding:ActivitySignupBinding by lazy {
        ActivitySignupBinding.inflate(layoutInflater)
    }
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.register.setOnClickListener {
            val email = binding.email.text.toString()
            val username = binding.username1.text.toString()
            val password = binding.password1.text.toString()
            val repassword = binding.repassword.text.toString()

            //check if any field is blank
            if(email.isEmpty() || username.isEmpty() || password.isEmpty() || repassword.isEmpty()){
                Toast.makeText(this,"Please fill the detail",Toast.LENGTH_LONG).show()
            }else if (password != repassword){
                Toast.makeText(this,"Check the password was same?",Toast.LENGTH_SHORT).show()

            }else{
                auth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this){
                        task->
                        if(task.isSuccessful){
                            Toast.makeText(this,"Registration Completed",Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this,Loginscreen::class.java))
                            finish()
                        }else{
                            Toast.makeText(this,"Registration Failed:${task.exception?.message}",Toast.LENGTH_SHORT).show()
                        }
                    }

            }

        }




    }
}