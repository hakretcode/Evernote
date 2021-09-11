package com.hakretcode.evernote.mvc.model


data class Note(
    var id: Int = 0,
    var title: String? = null,
    var desc: String? = null,
    var date: String? = null,
    var body: String? = null
) {
    constructor() : this(1, "Nota Test")

}