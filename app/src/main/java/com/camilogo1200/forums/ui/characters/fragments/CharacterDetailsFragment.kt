package com.camilogo1200.forums.ui.characters.fragments

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.camilogo1200.forums.R
import com.camilogo1200.forums.databinding.FragmentCharacterDetailsBinding
import com.camilogo1200.forums.domain.models.CharacterStatus
import com.camilogo1200.forums.utils.loadImage

class CharacterDetailsFragment : Fragment() {

    private lateinit var binding: FragmentCharacterDetailsBinding
    val args: CharacterDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_character_details,
            container,
            false
        )

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(args.character) {
            binding.gender.text =
                SpannableStringBuilder(gender.name.lowercase())
            binding.name.text = SpannableStringBuilder(name)
            binding.location.text = SpannableStringBuilder(location.name)
            binding.status.text = SpannableStringBuilder(status.name)
            binding.specie.text = SpannableStringBuilder(species)
            binding.origin.text = SpannableStringBuilder(origin.name)
            drawStatus(status)
            image?.let {
                binding.profileImage.loadImage(it)
            }
        }
    }


    private fun drawStatus(status: CharacterStatus) {
        val drawable = when (status) {
            CharacterStatus.ALIVE -> R.drawable.ic_dot_alive
            CharacterStatus.DEAD -> R.drawable.ic_dot_dead
            else -> R.drawable.ic_dot_unknown
        }
        binding.statusImg.background =
            ResourcesCompat.getDrawable(binding.root.resources, drawable, null)
    }

}
