package com.camilogo1200.forums.ui.characters.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.camilogo1200.forums.domain.usecases.characters.interfaces.GetAllCharacters
import com.camilogo1200.forums.ui.characters.view_states.GetAllCharactersViewState
import com.camilogo1200.forums.utils.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val getAllCharactersUC: GetAllCharacters
) : ViewModel() {

    private val _viewState = MutableLiveData<GetAllCharactersViewState>()
    val viewState = _viewState.asLiveData()

    fun setViewState(newViewState: GetAllCharactersViewState) {
        _viewState.value = newViewState
    }

    fun initView() {
        loadCharacters()
    }

    private fun loadCharacters() {
        viewModelScope.launch {
            val result = getAllCharactersUC.invoke()
            if (result.isSuccess) {
                setViewState(GetAllCharactersViewState.FetchedAllCharacters(result.getOrNull()))
            } else {
                setViewState(GetAllCharactersViewState.FailFetchingCharacters)
            }
        }
    }

    fun fetchMoreCharacters() {
        loadCharacters()
    }
}
