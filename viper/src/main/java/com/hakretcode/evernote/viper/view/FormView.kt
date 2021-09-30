package com.hakretcode.evernote.viper.view

import com.hakretcode.evernote.viper.data.model.Note

interface FormView {
    fun displayNote(note: Note)
    fun displayError(message: String)
}