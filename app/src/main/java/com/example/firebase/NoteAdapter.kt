package com.example.firebase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebase.databinding.ItemnotesBinding


class NoteAdapter(private val notes: List<NoteItems>):RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemnotesBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NoteViewHolder(binding)

    }

    override fun getItemCount(): Int {
        return notes.size

    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note =  notes[position]
        holder.bind(note)

    }
    class NoteViewHolder(private val binding:ItemnotesBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(note: NoteItems) {
            binding.textView3.text = note.title
            binding.textView4.text = note.description

        }


    }
}



