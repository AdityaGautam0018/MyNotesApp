package com.example.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NotesScreen : Fragment(), NotesAdapter.OnNoteClickListener, AddNoteDialogFragment.AddNoteDialogListener {

    private lateinit var notesAdapter: NotesAdapter
    private val notes = mutableListOf<Note>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_notes_screen, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        notesAdapter = NotesAdapter(notes, this)
        recyclerView.adapter = notesAdapter
        notes.addAll(getDummyNotes())
        notesAdapter.notifyDataSetChanged()

        val addNoteButton = view.findViewById<Button>(R.id.addNoteButton)
        addNoteButton.setOnClickListener {
            val dialog = AddNoteDialogFragment(this)
            dialog.show(childFragmentManager, "AddNoteDialogFragment")
        }

        return view
    }

    override fun onNoteClick(note: Note) {
    }

    override fun onNoteDelete(note: Note) {
        notes.remove(note)
        notesAdapter.notifyDataSetChanged()
    }

    private fun getDummyNotes(): List<Note> {
        return listOf(

        )
    }

    override fun onDialogPositiveClick(note: Note) {
        notes.add(note)
        notesAdapter.notifyItemInserted(notes.size - 1)
    }
}
