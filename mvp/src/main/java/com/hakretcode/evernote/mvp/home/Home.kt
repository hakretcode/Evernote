package com.hakretcode.evernote.mvp.home

import com.hakretcode.evernote.mvp.model.Note

interface Home {
    interface Presenter {
        fun getAllNotes()
        fun stop()
    } interface View {
        fun displayNotes(notes: List<Note>)
        fun displayEmptyNote()
        fun displayError(notes: String)
    }
}