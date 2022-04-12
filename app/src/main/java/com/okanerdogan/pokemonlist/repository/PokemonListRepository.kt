package com.okanerdogan.pokemonlist.repository

import com.okanerdogan.pokemonlist.data.PokemonListModel

interface PokemonListRepository {
    suspend fun get(limit: Int): PokemonListModel
}