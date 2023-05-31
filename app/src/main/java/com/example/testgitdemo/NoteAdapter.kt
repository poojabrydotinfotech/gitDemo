package com.example.testgitdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.testgitdemo.NoteAdapter.NotViewHolder

class NoteAdapter :ListAdapter<NoteModel,NotViewHolder>(WORDS_COMPARATOR){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotViewHolder {
        return NotViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: NotViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.title)
    }

    class NotViewHolder(view: View):RecyclerView.ViewHolder(view) {
        private val wordItemView: TextView = view.findViewById(R.id.title)

        fun bind(text: String?) {
            wordItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): NotViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.raw_detail, parent, false)
                return NotViewHolder(view)
            }
        }
    }


    companion object {
        private val WORDS_COMPARATOR = object : DiffUtil.ItemCallback<NoteModel>() {
            override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
                return oldItem.title == newItem.title
            }
        }
    }



}



