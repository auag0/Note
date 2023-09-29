package com.anago.note.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.anago.note.R
import com.anago.note.adapters.NoteListAdapter
import com.anago.note.database.entities.Note
import com.anago.note.viewmodels.NoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class NoteListFragment : Fragment() {
    private val viewModel: NoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(
            R.layout.fragment_note_list,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fab: FloatingActionButton = view.findViewById(R.id.fab)
        fab.setOnClickListener {
            goToEditNote(null)
        }

        val noteListAdapter = NoteListAdapter(requireContext(), ::onNoteClicked)
        val layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
        val noteList: RecyclerView = view.findViewById(R.id.note_list)
        noteList.adapter = noteListAdapter
        noteList.layoutManager = layoutManager

        viewModel.allNote.observe(viewLifecycleOwner) {
            noteListAdapter.noteList = it
        }
    }

    private fun onNoteClicked(note: Note) {
        goToEditNote(note)
    }

    private fun goToEditNote(note: Note?) {
        val fragment = NoteEditFragment.newInstance(note)
        parentFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(android.R.id.content, fragment)
            .commit()
    }
}













