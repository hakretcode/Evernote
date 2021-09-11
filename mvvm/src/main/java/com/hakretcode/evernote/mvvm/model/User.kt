package com.hakretcode.evernote.mvvm.model

class User(private val note: Note) {
    fun showNoteTitle() {
        println("titulo: ${note.title}")
    }
}