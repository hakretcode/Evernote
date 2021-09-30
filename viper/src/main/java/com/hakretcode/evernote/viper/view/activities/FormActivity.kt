package com.hakretcode.evernote.viper.view.activities

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MenuItem
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.hakretcode.evernote.viper.R
import com.hakretcode.evernote.viper.data.model.Note
import com.hakretcode.evernote.viper.presenter.FormPresenter
import com.hakretcode.evernote.viper.view.FormView
import kotlinx.android.synthetic.main.activity_form.*
import kotlinx.android.synthetic.main.content_form.*

class FormActivity : AppCompatActivity(), FormView, TextWatcher {
    private lateinit var presenter: FormPresenter
    private var toSave: Boolean = false
    private var noteId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        presenter = FormPresenter(this)

        noteId = intent.extras?.getInt("noteId")

        setupViews()
    }

    override fun onStart() {
        super.onStart()
        noteId?.let { presenter.getNote(it) }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    private fun setupViews() {
        setSupportActionBar(toolbar)
        toggleToolbar(R.drawable.ic_arrow_back_black_24dp)

        note_title.addTextChangedListener(this)
        note_editor.addTextChangedListener(this)
    }

    private fun toggleToolbar(@DrawableRes icon: Int) {
        supportActionBar?.let {
            it.title = null
            val upArrow = ContextCompat.getDrawable(this, icon)
            val colorFilter =
                PorterDuffColorFilter(
                    ContextCompat.getColor(this, R.color.colorAccent),
                    PorterDuff.Mode.SRC_ATOP
                )
            upArrow?.colorFilter = colorFilter
            it.setHomeAsUpIndicator(upArrow)
            it.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun displayError(message: String) =
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()

    override fun displayNote(note: Note) {
        note_title.setText(note.title)
        note_editor.setText(note.body)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            if (toSave && noteId == null) presenter.saveNote(note_title
                .text.toString(), note_editor.text.toString())
            else presenter.goToHome()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
    }

    override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
        toSave = if (note_editor.text.toString().isEmpty()
            && note_title.text.toString().isEmpty()
        ) {
            toggleToolbar(R.drawable.ic_arrow_back_black_24dp)
            false
        } else {
            toggleToolbar(R.drawable.ic_done_black_24dp)
            true
        }
    }

    override fun afterTextChanged(editable: Editable) {
    }

}