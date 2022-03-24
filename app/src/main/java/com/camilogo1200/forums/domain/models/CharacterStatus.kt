package com.camilogo1200.forums.domain.models

enum class CharacterStatus(val status: String) {
    ALIVE("Alive"), DEAD("Dead"), UNKNOWN("unknown");

}

fun ConvertToStatusEnum(status: String): CharacterStatus {
    return when (status) {
        "Alive" -> CharacterStatus.ALIVE
        "Dead" -> CharacterStatus.DEAD
        else -> CharacterStatus.UNKNOWN
    }
}
