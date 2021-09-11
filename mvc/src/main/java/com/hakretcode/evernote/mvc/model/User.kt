package com.hakretcode.evernote.mvc.model

class User(private val note: Note) {
    fun showNoteTitle() {
        println("titulo: ${note.title}")
    }
}