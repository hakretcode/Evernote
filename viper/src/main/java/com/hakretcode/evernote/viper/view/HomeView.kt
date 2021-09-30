package com.hakretcode.evernote.viper.view

import com.hakretcode.evernote.viper.data.model.Note

interface HomeView {
        fun displayNotes(notes: List<Note>)
        fun displayError(message: String)
}