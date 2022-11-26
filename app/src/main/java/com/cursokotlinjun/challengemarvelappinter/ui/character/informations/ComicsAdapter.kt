package com.cursokotlinjun.challengemarvelappinter.ui.character.informations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cursokotlinjun.challengemarvelappinter.R
import com.cursokotlinjun.challengemarvelappinter.databinding.ViewComicItemBinding
import com.cursokotlinjun.challengemarvelappinter.domain.model.Comic
import com.cursokotlinjun.challengemarvelappinter.ui.base.BaseAdapter
import com.cursokotlinjun.challengemarvelappinter.util.extensions.toYear

class ComicsAdapter: BaseAdapter<Comic, ComicsAdapter.ComicsViewHolder>() {

    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) =
        holder.bind(list[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsViewHolder =
        ComicsViewHolder(ViewComicItemBinding.bind(LayoutInflater.from(parent.context).inflate(
            R.layout.view_comic_item,
         parent, false)),)

inner class ComicsViewHolder(
    private val binding: ViewComicItemBinding
): RecyclerView.ViewHolder(binding.root){
    fun bind(item: Comic){
        binding.apply {
            textViewComicName.text = item.title
                textViewComicYear.text = item.dates.first().date.toYear()
                    // item.data
            //[0].data.substringBefore("-")
        }
    }
}
}