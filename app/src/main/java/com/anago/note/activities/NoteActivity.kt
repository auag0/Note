package com.anago.note.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anago.note.fragments.NoteListFragment

class NoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            val fragment = NoteListFragment()
            supportFragmentManager.beginTransaction()
                .add(android.R.id.content, fragment)
                .commit()
        }
    }
}