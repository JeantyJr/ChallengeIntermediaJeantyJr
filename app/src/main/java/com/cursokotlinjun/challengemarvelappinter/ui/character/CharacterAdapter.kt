package com.cursokotlinjun.challengemarvelappinter.ui.character

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cursokotlinjun.challengemarvelappinter.R
import com.cursokotlinjun.challengemarvelappinter.databinding.ViewCharacterItemBinding
import com.cursokotlinjun.challengemarvelappinter.domain.model.Character
import com.cursokotlinjun.challengemarvelappinter.ui.base.BaseAdapter
import com.cursokotlinjun.challengemarvelappinter.util.binding.setImage

class CharacterAdapter: BaseAdapter<Character, CharacterAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder =
        CharacterViewHolder(ViewCharacterItemBinding.bind(LayoutInflater.from(parent.context).inflate(
            R.layout.view_character_item, parent, false)), onClickListener = onClickListener)

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(list[position])
    }





class CharacterViewHolder(
    private val binding: ViewCharacterItemBinding,
    private val onClickListener: ((Character) -> Unit)?
): RecyclerView.ViewHolder(binding.root){

    fun bind(item: Character) {
        binding.apply {

            setImage(characterImg, item.thumbnail)
            textViewChar.text = item.name
            textViewDescription.text = item.description

            root.setOnClickListener {
                onClickListener?.invoke(item)
            }
        }

    }
}

}