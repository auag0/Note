package com.anago.note.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anago.note.R
import com.anago.note.database.entities.Note
import com.google.android.material.textview.MaterialTextView

class NoteListAdapter(
    private val context: Context,
    private val onNoteClicked: (Note) -> Unit
) : RecyclerView.Adapter<NoteListAdapter.ViewHolder>() {
    @SuppressLint("NotifyDataSetChanged")
    var noteList: List<Note> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: MaterialTextView = itemView.findViewById(R.id.title)
        val content: MaterialTextView = itemView.findViewById(R.id.content)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.list_note_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = noteList[position]

        holder.title.text = note.title.trim()
        holder.content.text = note.content.trim()

        holder.itemView.setOnClickListener {
            onNoteClicked(note)
        }
    }

    override fun getItemCount(): Int {
        return noteList.size
    }
}