package com.example.hw61.presentation.view.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hw61.databinding.ItemCharacterBinding

class CharacterAdapter : PagingDataAdapter<Character, CharacterAdapter.CharacterViewHolder>(DiffCallback()) {

    class CharacterViewHolder(val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemCharacterBinding.inflate(inflater, parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
       val character = getItem(position) ?: return
        holder.binding.apply {
            tvName.text = character.toString()
            tvSpecies.text = character.toString()

            Log.e("CharacterAdapter", "Loading image URL: ${character.toString()}")
            Glide.with(holder.itemView.context)
                .load(character.toString())
                .into(ivCharacter)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }
    }
}
