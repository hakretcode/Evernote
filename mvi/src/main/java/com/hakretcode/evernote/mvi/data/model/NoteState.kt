package com.hakretcode.evernote.mvi.data.model

sealed class NoteState {
    object LoadingState: NoteState()
    class DataState(val data: List<Note>): NoteState()
    class ErrorState(val data: String): NoteState()
    class SingleDataState(val data: Note): NoteState()
    object FinishState : NoteState()
}
