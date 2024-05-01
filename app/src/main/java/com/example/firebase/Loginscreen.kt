package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.firebase.databinding.ActivityLoginscreenBinding
import com.google.firebase.auth.FirebaseAuth

class Loginscreen : AppCompatActivity() {
    private val binding:ActivityLoginscreenBinding by lazy{
        ActivityLoginscreenBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.button2.setOnClickListener {
            startActivity(Intent(this,Signup::class.java))
            finish()
        }
        binding.login.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }


    }
}