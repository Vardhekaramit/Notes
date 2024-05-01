package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.firebase.databinding.ActivityAtnoteBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Atnote : AppCompatActivity() {
    private val binding:ActivityAtnoteBinding by lazy{
        ActivityAtnoteBinding.inflate(layoutInflater)
    }
    private lateinit var databaseReference: DatabaseReference
    private lateinit var  auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        databaseReference = FirebaseDatabase.getInstance().reference
        auth = FirebaseAuth.getInstance()

        binding.save.setOnClickListener {
            val title = binding.titile.text.toString()
            val description = binding.description.text.toString()

            if(title.isEmpty() && description.isEmpty()){
                Toast.makeText(this,"Fill both field",Toast.LENGTH_LONG).show()
            }else{
                val currentuser = auth.currentUser
                currentuser?.let{
                    user->
                    //general a unique key for the note
                    val noteKey = databaseReference.child("users").child(user.uid).child("notes").push().key

                    //note item instance
                    val noteitem = NoteItems(title, description)
                    if(noteKey != null){

                        databaseReference.child("users").child(user.uid).child("notes").child(noteKey).setValue(noteitem)
                            .addOnCompleteListener{
                                task->
                                if(task.isSuccessful){
                                    Toast.makeText(this,"Note save success",Toast.LENGTH_LONG).show()
                                    finish()
                                }else{
                                    Toast.makeText(this,"Note save not success",Toast.LENGTH_LONG).show()
                                }
                            }

                    }
                }



            }


        }
    }
}