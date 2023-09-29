package com.anago.note.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.anago.note.R
import com.anago.note.database.entities.Note
import com.anago.note.utils.BundleCompat.getCSerializable
import com.anago.note.viewmodels.NoteViewModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteEditFragment : Fragment() {
    private val viewModel: NoteViewModel by viewModels({ requireActivity() })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(
            R.layout.fragment_note_edit,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val note = arguments?.getCSerializable("note") ?: Note.empty()

        val titleEditText: TextInputEditText = view.findViewById(R.id.titleEditText)
        val contentEditText: TextInputEditText = view.findViewById(R.id.contentEditText)

        titleEditText.setText(note.title)
        contentEditText.setText(note.content)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            lifecycleScope.launch(Dispatchers.Default) {
                val newTitle = titleEditText.text?.toString() ?: ""
                val newContent = contentEditText.text?.toString() ?: ""
                if (newTitle.isBlank() && newContent.isBlank()) {
                    viewModel.deleteNoteById(note.id)
                } else {
                    saveCurrentNote(note.id, newTitle, newContent)
                }
                withContext(Dispatchers.Main) {
                    parentFragmentManager.popBackStack()
                }
            }
        }
    }

    private suspend fun saveCurrentNote(noteId: Int, title: String, content: String) {
        return withContext(Dispatchers.Default) {
            val newNote = Note(
                id = noteId,
                title = title,
                content = content,
                lastEditedTime = System.currentTimeMillis()
            )
            viewModel.insertNote(newNote)
        }
    }

    companion object {
        fun newInstance(note: Note?): NoteEditFragment {
            return NoteEditFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("note", note)
                }
            }
        }
    }
}