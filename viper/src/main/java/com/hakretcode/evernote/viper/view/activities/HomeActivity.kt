package com.hakretcode.evernote.viper.view.activities

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import com.hakretcode.evernote.viper.R
import com.hakretcode.evernote.viper.data.model.Note
import com.hakretcode.evernote.viper.presenter.HomePresenter
import com.hakretcode.evernote.viper.view.HomeView
import com.hakretcode.evernote.viper.view.adapters.NoteAdapter
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.content_home.*

class HomeActivity : AppCompatActivity(), HomeView,
    NavigationView.OnNavigationItemSelectedListener {
    private lateinit var presenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        presenter = HomePresenter(this)
        setupViews()
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

        fab.setOnClickListener { presenter.goToFormActivity() }
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun displayError(message: String) = Toast.makeText(this, message, Toast.LENGTH_LONG).show()

    override fun displayNotes(notes: List<Note>) {
        // progress
        if (notes.isNotEmpty()) home_recycler_view.adapter =
            NoteAdapter(notes) { note -> presenter.goToFormActivity(note.id) }
        else {
            // no data
        }
    }

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