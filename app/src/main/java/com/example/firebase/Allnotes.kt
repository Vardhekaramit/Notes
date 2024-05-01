package com.example.firebase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase.databinding.ActivityAllnotesBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener



class Allnotes : AppCompatActivity() {
    private val binding: ActivityAllnotesBinding by lazy {
        ActivityAllnotesBinding.inflate(layoutInflater)
    }
    private lateinit var databaseReference: DatabaseReference

    private lateinit var auth: FirebaseAuth
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        databaseReference = FirebaseDatabase.getInstance().reference
        auth = FirebaseAuth.getInstance()

        val currentuser = auth.currentUser
        currentuser?.let {
            user->
            val noteReference = databaseReference.child("users").child(user.uid).child("notes")
            noteReference.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val noteList = mutableListOf<NoteItems>()
                    for(notesnapshot in snapshot.children){
                        val note = notesnapshot.getValue(NoteItems::class.java)
                        note?.let {
                            noteList.add(it)
                        }
                    }
                    val adapter = NoteAdapter(noteList)
                    recyclerView.adapter = adapter
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        }


    }
}