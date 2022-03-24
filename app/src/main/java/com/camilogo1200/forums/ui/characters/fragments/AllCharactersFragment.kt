package com.camilogo1200.forums.ui.characters.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.camilogo1200.forums.R
import com.camilogo1200.forums.databinding.FragmentAllCharactersBinding
import com.camilogo1200.forums.domain.models.Character
import com.camilogo1200.forums.ui.characters.adapters.AllCharactersAdapter
import com.camilogo1200.forums.ui.characters.view_models.CharactersViewModel
import com.camilogo1200.forums.ui.characters.view_states.GetAllCharactersViewState
import com.camilogo1200.forums.ui.characters.view_states.GetAllCharactersViewState.FailFetchingCharacters
import com.camilogo1200.forums.ui.characters.view_states.GetAllCharactersViewState.FetchedAllCharacters
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AllCharactersFragment : Fragment() {

    private lateinit var binding: FragmentAllCharactersBinding
    private val viewModel: CharactersViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_all_characters,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.allCharactersRecycler.adapter = AllCharactersAdapter {
            navigateToCharacterDetails(it)
        }
        bindObservers()
        bindAdapterInfiniteScrolling()
        viewModel.initView()
    }

    private fun navigateToCharacterDetails(character: Character) {
        val navController = findNavController()
        val action =
            AllCharactersFragmentDirections.actionAllCharactersFragmentToCharacterDetailsFragment(
                character
            )
        navController.navigate(action)

    }

    private fun bindAdapterInfiniteScrolling() {
        binding.allCharactersRecycler.addOnScrollListener(
            object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE || newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                        recyclerView.layoutManager?.let {
                            val childCount = it.childCount
                            val totalItemCount = it.itemCount
                            val pastVisiblesItems =
                                (it as LinearLayoutManager).findFirstVisibleItemPosition()

                            if ((childCount + pastVisiblesItems) >= totalItemCount) {
                                viewModel.fetchMoreCharacters()
                                Log.v("...", "Last Item Wow !");
                            }
                        }
                    }
                    super.onScrollStateChanged(recyclerView, newState)
                }
            })
    }

    private fun bindObservers() {
        viewModel.viewState.observe(viewLifecycleOwner, ::handleViewState)
    }

    private fun handleViewState(viewState: GetAllCharactersViewState?) {
        viewState?.let { state ->
            when (state) {
                is FetchedAllCharacters -> handleFetchedCharacters(state.characters)
                is FailFetchingCharacters -> handleFailureFetingCharacters()
            }
        }
    }

    private fun handleFetchedCharacters(characters: List<Character>?) {
        characters?.let {
            (binding.allCharactersRecycler.adapter as AllCharactersAdapter).apply {
                setData(characters)
            }
        }
    }

    private fun handleFailureFetingCharacters() {
        Snackbar.make(binding.root, "Failed to fetch characters.", Snackbar.LENGTH_LONG)
            .setAction("RETRY") {
                viewModel.initView()
            }.show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}
