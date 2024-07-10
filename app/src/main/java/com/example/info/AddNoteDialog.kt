package com.example.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment

class AddNoteDialogFragment(private val listener: AddNoteDialogListener) : DialogFragment() {

    interface AddNoteDialogListener {
        fun onDialogPositiveClick(note: Note)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_note_dialog, container, false)

        val titleEditText = view.findViewById<EditText>(R.id.noteTitleEditText)
        val contentEditText = view.findViewById<EditText>(R.id.noteContentEditText)
        val saveButton = view.findViewById<Button>(R.id.saveButton)
        val cancelButton = view.findViewById<Button>(R.id.cancelButton)

        saveButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val content = contentEditText.text.toString()
            if (title.isNotEmpty() && content.isNotEmpty()) {
                listener.onDialogPositiveClick(Note(
                    id = System.currentTimeMillis(),
                    title = title,
                    content = content
                ))
                dismiss()
            }
        }

        cancelButton.setOnClickListener {
            dismiss()
        }

        return view
    }
}