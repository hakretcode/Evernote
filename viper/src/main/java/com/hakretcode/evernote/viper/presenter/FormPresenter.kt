package com.hakretcode.evernote.viper.presenter

import android.app.Activity
import com.hakretcode.evernote.viper.data.model.Note
import com.hakretcode.evernote.viper.interactor.FormInteractor
import com.hakretcode.evernote.viper.router.FormRouter
import com.hakretcode.evernote.viper.view.FormView

class FormPresenter(
    private val view: FormView,
    private val interactor: FormInteractor = FormInteractor(),
    private val router: FormRouter = FormRouter()
) {
    fun getNote(noteId: Int) = interactor.fetchNote(noteId,
        { view.displayNote(it) }, { view.displayError("Erro ao exibir erro") })

    fun goToHome() = router.finishActivity(view as Activity)

    fun saveNote(title: String, body: String) {
        if (title.isEmpty() || body.isEmpty()) {
            view.displayError("Type the title and the text")
            return
        }
        interactor.createNote(Note(title = title, body = body),
            { goToHome() }, { view.displayError("Error to create note") })
    }

    fun onDestroy() = interactor.dispose()
}
