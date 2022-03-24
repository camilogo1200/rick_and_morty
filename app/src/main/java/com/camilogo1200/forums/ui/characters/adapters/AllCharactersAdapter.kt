package com.camilogo1200.forums.ui.characters.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.camilogo1200.forums.R
import com.camilogo1200.forums.databinding.CharacterCardBinding
import com.camilogo1200.forums.domain.models.Character

class AllCharactersAdapter(private val listener: (Character) -> Unit) :
    RecyclerView.Adapter<AllCharactersViewHolder>() {

    fun setData(items: List<Character>) {
        val oldPosition = this.characterList.size
        characterList.addAll(items)
        notifyItemRangeChanged(oldPosition, characterList.size)
        notifyDataSetChanged()
    }

    private val characterList = mutableListOf<Character>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllCharactersViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<CharacterCardBinding>(
                layoutInflater,
                R.layout.character_card,
                parent,
                false
            )

        return AllCharactersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AllCharactersViewHolder, position: Int) {
        val character = characterList[position]
        holder.binding.root.setOnClickListener { listener.invoke(character) }
        holder.bind(character)
    }


    override fun getItemCount(): Int {
        return characterList.size;
    }
}
