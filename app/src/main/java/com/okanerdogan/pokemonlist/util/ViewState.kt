package com.okanerdogan.pokemonlist.util

import com.okanerdogan.pokemonlist.data.PokemonDetailModel

sealed class ViewState {
    object Loading : ViewState()
    data class Success(val data: PokemonDetailModel) : ViewState()
    data class Error(val message: String) : ViewState()
}