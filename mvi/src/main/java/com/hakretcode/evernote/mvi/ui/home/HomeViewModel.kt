package com.hakretcode.evernote.mvi.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hakretcode.evernote.mvi.data.NoteRepository
import com.hakretcode.evernote.mvi.data.model.NoteState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: NoteRepository = NoteRepository()) : ViewModel() {
    private val _noteState = MutableLiveData<NoteState>()
    val noteState: LiveData<NoteState> = _noteState
    private var disposable: Disposable? = null

    init { viewModelScope.launch { fetchNotes() } }

    fun unbind() { disposable?.dispose() }

    private fun fetchNotes() {
        _noteState.value = NoteState.LoadingState
        disposable = repository.listNotes()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ _noteState.value = NoteState.DataState(it) },
                { _noteState.value = NoteState.ErrorState(it.localizedMessage ?: "Unknown error") })
    }
}