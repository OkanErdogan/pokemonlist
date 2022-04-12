package com.okanerdogan.pokemonlist.network

import com.okanerdogan.pokemonlist.data.PokemonDetailModel
import com.okanerdogan.pokemonlist.data.PokemonListModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApiService {
    @GET("pokemon")
    suspend fun get(
        @Query("limit") limit: Int
    ): PokemonListModel


    @GET("pokemon/{name}")
    suspend fun getDetail(
        @Path("name") name: String
    ): PokemonDetailModel
}