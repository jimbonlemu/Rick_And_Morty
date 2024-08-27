package com.jimbonlemu.rickandmorty.core.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jimbonlemu.rickandmorty.core.databinding.ItemCharacterBinding
import com.jimbonlemu.rickandmorty.core.domain.model.Character


class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.ListViewHolder>() {

    private var listData = ArrayList<Character>()
    var onItemClick: ((Character) -> Unit)? = null

    fun setData(newListData: List<Character>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        listData.reverse()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val binding = ItemCharacterBinding.bind(itemView)
        fun bind(data: Character) {
            with(binding) {
                Glide.with(root)
                    .load(data.image)
                    .into(ivCharacter)
                tvCharName.text = data.name
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}