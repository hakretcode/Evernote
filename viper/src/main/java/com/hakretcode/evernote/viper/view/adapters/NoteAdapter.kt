package com.hakretcode.evernote.viper.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hakretcode.evernote.viper.R
import com.hakretcode.evernote.viper.data.model.Note
import com.hakretcode.evernote.viper.databinding.ListItemNoteBinding
import kotlinx.android.synthetic.main.list_item_note.view.*

class NoteAdapter(private val notes: List<Note>, val onClickListener: (Note) -> Unit) :
    RecyclerView.Adapter<NoteAdapter.NoteView>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteView =
        NoteView(DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.list_item_note, parent, false
        ))

    override fun onBindViewHolder(holder: NoteView, position: Int) { notes[position].apply {
        holder.binding.note = this
        holder.itemView.setOnClickListener { onClickListener.invoke(this) }
    }}

    override fun getItemCount(): Int = notes.size

    inner class NoteView(val binding: ListItemNoteBinding) : RecyclerView.ViewHolder(binding.root)
}