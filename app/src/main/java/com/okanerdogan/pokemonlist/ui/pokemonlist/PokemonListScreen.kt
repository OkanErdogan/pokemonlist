package com.okanerdogan.pokemonlist.ui

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.okanerdogan.pokemonlist.ui.pokemonlist.NothingHere
import com.okanerdogan.pokemonlist.ui.pokemonlist.PAGE_SIZE
import com.okanerdogan.pokemonlist.ui.pokemonlist.PokemonCard
import com.okanerdogan.pokemonlist.ui.pokemonlist.PokemonListViewModel
import com.okanerdogan.pokemonlist.util.InternetConnectionCheckerLive

private lateinit var internetConnectionCheckerLive: InternetConnectionCheckerLive
private var isInternetAvailable: Boolean = false;

@Composable
fun PokemonListScreen(
    navController: NavHostController,
    context: AppCompatActivity,
    pokemonListViewModel: PokemonListViewModel = hiltViewModel()
) {

    internetConnectionCheckerLive = InternetConnectionCheckerLive(context)
    internetConnectionCheckerLive.observe(context) { isNetworkAvailable ->
        isNetworkAvailable?.let {
            if (!isInternetAvailable && it) {
                pokemonListViewModel.triggerNextPage(true)
            }
            isInternetAvailable = it
        }
    }

    val pokemons = pokemonListViewModel.pokemons.value
    val page = pokemonListViewModel.page.value

    Box(
        modifier = Modifier
            .background(color = MaterialTheme.colors.surface)
    ) {
        if (pokemons.isEmpty()) {
            NothingHere()
        } else {
            LazyColumn {
                itemsIndexed(items = pokemons) { index, item ->
                    if ((index + 1) >= page * PAGE_SIZE && isInternetAvailable) {
                        pokemonListViewModel.triggerNextPage(false)
                    }
                    PokemonCard(
                        navController = navController,
                        pokemonModel = item,
                        index = index + 1
                    )
                }
            }
        }
    }
}