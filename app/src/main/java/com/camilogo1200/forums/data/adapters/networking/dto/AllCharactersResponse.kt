package com.camilogo1200.forums.data.adapters.networking.dto

import android.os.Parcelable
import com.camilogo1200.forums.domain.models.Character
import com.camilogo1200.forums.domain.models.ConvertToGenderEnum
import com.camilogo1200.forums.domain.models.ConvertToStatusEnum
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
data class AllCharactersResponse(
    val info: InformationDto,
    val results: List<CharacterDto>
)

@Serializable
data class InformationDto(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)

@Serializable
data class CharacterDto(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: RyMLocation,
    val location: RyMLocation,
    val image: String?,
    val episode: List<String>?,
    val created: String
) {
    fun toModel(): Character {
        return Character(
            id,
            name,
            ConvertToStatusEnum(status),
            species,
            type,
            ConvertToGenderEnum(gender),
            origin,
            location,
            image,
            episode,
            created
        )
    }
}

@Serializable
@Parcelize
data class RyMLocation(
    val name: String,
    val url: String
) : Parcelable
