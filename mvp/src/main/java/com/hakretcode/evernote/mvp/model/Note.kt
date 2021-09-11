package com.hakretcode.evernote.mvp.model


data class Note(
    var id: Int = 0,
    var title: String = "",
    var desc: String? = null,
    var date: String? = null,
    var body: String = ""
)