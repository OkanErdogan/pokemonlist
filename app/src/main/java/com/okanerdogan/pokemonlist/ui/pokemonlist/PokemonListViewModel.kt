package com.okanerdogan.pokemonlist.ui.pokemonlist

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okanerdogan.pokemonlist.data.PokemonModel
import com.okanerdogan.pokemonlist.repository.PokemonListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

const val PAGE_SIZE = 25
const val STATE_KEY_DEFAULT = "pokemonlist.state.default.key"


@HiltViewModel
class PokemonListViewModel
@Inject
constructor(
    private val repository: PokemonListRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val pokemons: MutableState<List<PokemonModel>> = mutableStateOf(ArrayList()) // Bu tanımı öğren.
    val page = mutableStateOf(0)
    var listScrollPosition = 0

    init {
        savedStateHandle.get<Int>(STATE_KEY_DEFAULT)?.let { i -> }

        if (listScrollPosition == 0) {
            triggerNextPage(false)
        }
    }


    fun triggerNextPage(isRefresh: Boolean) {
        viewModelScope.launch {
            try {
                if (isRefresh) {
                    refreshPage()
                } else {
                    nextPage()
                }
            } catch (ex: UnknownHostException) {
                Log.e("LOG", "Internet connection lost", ex)
            }
        }
    }

    private suspend fun nextPage() {
        this.page.value++
        refreshPage()
    }


    private suspend fun refreshPage() {
        if (page.value > 0) {
            val pokemonListResult = repository.get(PAGE_SIZE * page.value)
            pokemons.value = pokemonListResult.results
        }
    }
}