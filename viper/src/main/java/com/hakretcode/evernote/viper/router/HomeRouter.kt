package com.hakretcode.evernote.viper.router

import android.content.Context
import android.content.Intent
import com.hakretcode.evernote.viper.view.activities.FormActivity

class HomeRouter {
    fun goToFormActivity(context: Context, noteId: Int?) {
        val intent = Intent(context, FormActivity::class.java)
        noteId?.also { intent.putExtra("noteId", it) }
        context.startActivity(intent)
    }
}