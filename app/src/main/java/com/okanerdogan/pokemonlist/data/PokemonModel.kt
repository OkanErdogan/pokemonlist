package com.okanerdogan.pokemonlist.data

import androidx.compose.runtime.Immutable
import com.google.gson.annotations.SerializedName

@Immutable
data class PokemonModel(
    @SerializedName("name") var name: String? = null,
    @SerializedName("url") var url: String? = null
)
