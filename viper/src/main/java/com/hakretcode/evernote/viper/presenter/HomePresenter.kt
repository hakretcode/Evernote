package com.hakretcode.evernote.viper.presenter

import android.content.Context
import com.hakretcode.evernote.viper.data.model.Note
import com.hakretcode.evernote.viper.interactor.HomeInteractor
import com.hakretcode.evernote.viper.router.HomeRouter
import com.hakretcode.evernote.viper.view.HomeView

class HomePresenter(
    private val view: HomeView,
    private val interactor: HomeInteractor = HomeInteractor(),
    private val router: HomeRouter = HomeRouter(),
) {
    fun onStart() {
        interactor.fetchNotes(this::onQuerySuccess, this::onQueryError)
    }

    fun goToFormActivity(noteId: Int? = null) = router.goToFormActivity(view as Context, noteId)
    fun onQuerySuccess(note: List<Note>) = view.displayNotes(note)
    fun onQueryError(t: Throwable) = view.displayError("Erro ao carregar as notas")
}
