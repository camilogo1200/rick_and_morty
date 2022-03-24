package com.camilogo1200.forums.ui.characters.adapters

import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.camilogo1200.forums.R
import com.camilogo1200.forums.databinding.CharacterCardBinding
import com.camilogo1200.forums.domain.models.Character
import com.camilogo1200.forums.domain.models.CharacterStatus
import com.camilogo1200.forums.utils.loadImage
import java.util.*

class AllCharactersViewHolder(dataBinding: CharacterCardBinding) :
    RecyclerView.ViewHolder(dataBinding.root) {

    val binding: CharacterCardBinding = dataBinding

    fun bind(character: Character) {
        with(binding) {
            name.text = character.name
            gender.text = character.gender.name.lowercase().replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    java.util.Locale.getDefault()
                ) else it.toString()
            }
            specie.text = character.species
            character.image?.let {
                profileImage.loadImage(character.image)
            } ?: loadPlaceHolder(binding)
            drawStatus(binding, character.status)
            executePendingBindings()
        }
    }


    private fun drawStatus(binding: CharacterCardBinding, status: CharacterStatus) {
        val drawable = when (status) {
            CharacterStatus.ALIVE -> R.drawable.ic_dot_alive
            CharacterStatus.DEAD -> R.drawable.ic_dot_dead
            else -> R.drawable.ic_dot_unknown
        }
        binding.status.background =
            ResourcesCompat.getDrawable(binding.root.resources, drawable, null)
    }

    private fun loadPlaceHolder(binding: CharacterCardBinding) {
        binding.profileImage
    }

}
