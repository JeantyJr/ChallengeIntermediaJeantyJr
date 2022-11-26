package com.cursokotlinjun.challengemarvelappinter.ui.event

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.cursokotlinjun.challengemarvelappinter.R
import com.cursokotlinjun.challengemarvelappinter.databinding.ViewEventsItemBinding
import com.cursokotlinjun.challengemarvelappinter.domain.model.EventDato
import com.cursokotlinjun.challengemarvelappinter.domain.model.Events
import com.cursokotlinjun.challengemarvelappinter.ui.base.BaseAdapter
import com.cursokotlinjun.challengemarvelappinter.util.binding.setImage

class EventAdapter
    (
        private val comicInformations: ComicInformations,
    var getAllComics: ((EventDato) -> Unit)? = null
)
    : BaseAdapter<EventDato, EventAdapter.EventsViewHolder>() {

    private var lastPositionClicked: Int? = null

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) =
        holder.bind(position)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        EventsViewHolder(
            ViewEventsItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), getAllComics)



   inner class EventsViewHolder(
        private val binding: ViewEventsItemBinding,
        private val getAllComics: ((EventDato) -> Unit)?
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(position:Int){
            val item = list[position]
            with(binding) {
                setImage(imageViewEvent, item.thumbnail)
                textViewName.text = item.title
                textViewDate.text = item.date
                recyclerComics.tvTitle.text = "COMICS TO DISCUSS"
                recyclerComics.root.isVisible = item.isVisible

                if (item.isVisible){
                    btnShowComics.rotation = 180F
                }
                else {
                    btnShowComics.rotation = 0F
                }
                root.setOnClickListener {
                    lastPositionClicked?.let {
                        if (it != position){
                            list[it].isVisible = false
                            notifyItemChanged(it)
                        }
                    }
                    item.isVisible = !item.isVisible
                    notifyItemChanged(position)
                    if(item.isVisible){
                        getAllComics?.invoke(item)
                    }
                    lastPositionClicked = position
                }
                }
                comicInformations.setComicsList(binding)
            }

    }
        interface ComicInformations{
            fun setComicsList(binding: ViewEventsItemBinding)
        }

}