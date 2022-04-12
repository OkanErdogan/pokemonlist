package com.okanerdogan.pokemonlist.data

import androidx.compose.runtime.Immutable
import com.example.example.Stats
import com.example.example.Types
import com.google.gson.annotations.SerializedName

@Immutable
data class PokemonDetailModel(
    @SerializedName("name") var name: String? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("weight") var weight: Int? = null,
    @SerializedName("height") var height: Int? = null,
    @SerializedName("stats") var stats: ArrayList<Stats> = arrayListOf(),
    @SerializedName("types") var types: ArrayList<Types> = arrayListOf(),
)