package com.camilogo1200.forums.domain.models

enum class Gender {
    FEMALE, MALE, GENDERLESS, UNKNOWN
}

fun ConvertToGenderEnum(gender: String): Gender {
    return when (gender) {
        "Female" -> Gender.FEMALE
        "Male" -> Gender.MALE
        "GenderLess" -> Gender.GENDERLESS
        else -> Gender.UNKNOWN
    }
}
