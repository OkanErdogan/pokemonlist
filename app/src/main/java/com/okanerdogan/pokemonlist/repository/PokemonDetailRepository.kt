package com.okanerdogan.pokemonlist.repository

import com.okanerdogan.pokemonlist.data.PokemonDetailModel

interface PokemonDetailRepository {
    suspend fun getDetail(name: String): PokemonDetailModel
}