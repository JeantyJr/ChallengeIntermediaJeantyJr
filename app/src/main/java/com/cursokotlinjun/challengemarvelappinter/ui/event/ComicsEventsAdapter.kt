package com.cursokotlinjun.challengemarvelappinter.ui.event

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cursokotlinjun.challengemarvelappinter.R
import com.cursokotlinjun.challengemarvelappinter.databinding.ViewEventsComicsItemBinding
import com.cursokotlinjun.challengemarvelappinter.domain.model.Comic
import com.cursokotlinjun.challengemarvelappinter.ui.base.BaseAdapter
import com.cursokotlinjun.challengemarvelappinter.util.extensions.toYear

class ComicsEventsAdapter: BaseAdapter<Comic, ComicsEventsAdapter.ComicsEventViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsEventViewHolder =
        ComicsEventViewHolder(
            ViewEventsComicsItemBinding
                .bind(LayoutInflater.from(parent.context).inflate(R.layout.view_events_comics_item, parent, false))
        )

    override fun onBindViewHolder(holder: ComicsEventViewHolder, position: Int) {
        holder.bind(list[position])
    }

   inner class ComicsEventViewHolder(private val binding: ViewEventsComicsItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(comic: Comic){
            binding.apply {
                textViewComicTitle.text = comic.title
                textViewComicYear.text = comic.dates.first().date.toYear()
            }
        }

    }

}