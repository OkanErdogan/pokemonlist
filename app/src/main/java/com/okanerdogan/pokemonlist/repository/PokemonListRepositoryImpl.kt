package com.okanerdogan.pokemonlist.repository

import com.okanerdogan.pokemonlist.data.PokemonListModel
import com.okanerdogan.pokemonlist.network.PokeApiService

class PokemonListRepositoryImpl(
    private val pokeApiService: PokeApiService
) : PokemonListRepository {
    override suspend fun get(limit: Int): PokemonListModel {
        return pokeApiService.get(limit)
    }
}