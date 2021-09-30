package com.hakretcode.evernote.mvi.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import com.hakretcode.evernote.mvi.R
import com.hakretcode.evernote.mvi.data.model.Note
import com.hakretcode.evernote.mvi.data.model.NoteState
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.content_home.*

class HomeActivity : AppCompatActivity(),
    NavigationView.OnNavigationItemSelectedListener {
    private val viewModel by viewModels<HomeViewModel>()

    override fun onStart() {
        super.onStart()
        viewModel.noteState.observe(this, { render(it) })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setupViews()
    }

    private fun render(state: NoteState) {
        when(state) {
            is NoteState.LoadingState -> renderLoadingState()
            is NoteState.DataState -> renderDataState(state.data)
            is NoteState.ErrorState -> renderErrorState(state.data)
        }
    }

    private fun renderLoadingState() = showToast("Start loading")

    private fun renderDataState(data: List<Note>) {
        if (data.isNotEmpty()) home_recycler_view.adapter =
            NoteAdapter(data) { note ->
                showToast(note.id.toString())
            }
    }

    private fun renderErrorState(message: String) = showToast(message)

    override fun onDestroy() {
        super.onDestroy()
        viewModel.unbind()
    }

    private fun setupViews() {
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)

        val divider = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        home_recycler_view.addItemDecoration(divider)
        home_recycler_view.layoutManager = LinearLayoutManager(this)

        fab.setOnClickListener { showToast("Coming soon") }
    }

    private fun showToast(message: String) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START))
            drawer_layout.closeDrawer(GravityCompat.START) else super.onBackPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.home, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        return if (id == R.id.action_settings) {
            true
        } else super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.nav_all_notes) {  }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}