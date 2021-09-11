package com.hakretcode.evernote.mvp.add

interface Add {
    interface Presenter {
        fun createNote(title: String, body: String )
        fun getNote(id: Int)
        fun stop()
    } interface View {
        fun displayNote(title: String, body: String)
        fun displayError(message: String)
        fun returnToHome()
    }
}