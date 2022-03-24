package com.camilogo1200.forums.ui.characters.view_states

import com.camilogo1200.forums.domain.models.Character

sealed interface GetAllCharactersViewState {
    data class FetchedAllCharacters(val characters: List<Character>?) : GetAllCharactersViewState
    object FailFetchingCharacters : GetAllCharactersViewState
}
