package com.okanerdogan.pokemonlist.repository

import com.okanerdogan.pokemonlist.data.PokemonDetailModel
import com.okanerdogan.pokemonlist.network.PokeApiService

class PokemonDetailRepositoryImpl(
    private val pokeApiService: PokeApiService
) : PokemonDetailRepository {
    override suspend fun getDetail(name: String): PokemonDetailModel {
        return pokeApiService.getDetail(name)
    }
}