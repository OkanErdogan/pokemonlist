package com.okanerdogan.pokemonlist.di

import com.okanerdogan.pokemonlist.network.PokeApiService
import com.okanerdogan.pokemonlist.repository.PokemonDetailRepository
import com.okanerdogan.pokemonlist.repository.PokemonDetailRepositoryImpl
import com.okanerdogan.pokemonlist.repository.PokemonListRepository
import com.okanerdogan.pokemonlist.repository.PokemonListRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PokemonRepositoryModule {

    @Singleton
    @Provides
    fun providePokemonListRepository(pokeApiService: PokeApiService): PokemonListRepository {
        return PokemonListRepositoryImpl(pokeApiService)
    }


    @Singleton
    @Provides
    fun providePokemonDetailRepository(pokeApiService: PokeApiService): PokemonDetailRepository {
        return PokemonDetailRepositoryImpl(pokeApiService)
    }
}