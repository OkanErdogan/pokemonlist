package com.okanerdogan.pokemonlist.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.okanerdogan.pokemonlist.ui.pokemondetail.PokemonDetailScreen
import com.okanerdogan.pokemonlist.ui.theme.GRNTTheme
import dagger.hilt.android.AndroidEntryPoint


@ExperimentalAnimationApi
@AndroidEntryPoint
class PokemonListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GRNTTheme(content = {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "pokemon_list_screen"
                ) {
                    composable("pokemon_list_screen") {
                        PokemonListScreen(navController, this@PokemonListActivity)
                    }

                    composable(
                        "pokemon_detail_screen/{pokemonName}",
                        arguments = listOf(navArgument("pokemonName") { type = NavType.StringType })
                    ) {
                        val pokemonName = it.arguments?.getString("pokemonName")

                        PokemonDetailScreen(pokemonName)

                    }
                }

            })
        }
    }
}