package com.camilogo1200.forums.domain.models

import android.os.Parcelable
import com.camilogo1200.forums.data.adapters.networking.dto.RyMLocation
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Character(
    val id: Int,
    val name: String,
    val status: CharacterStatus,
    val species: String,
    val type: String,
    val gender: Gender,
    val origin: RyMLocation,
    val location: RyMLocation,
    val image: String?,
    val episodes: List<String>?,
    val created: String
) : Parcelable
