package com.okanerdogan.pokemonlist.ui.pokemondetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.okanerdogan.pokemonlist.R
import com.okanerdogan.pokemonlist.data.PokemonDetailModel
import com.okanerdogan.pokemonlist.ui.pokemonlist.loadPicture
import com.okanerdogan.pokemonlist.util.ViewState
import java.util.*

@Composable
fun PokemonDetailScreen(
    pokemonName: String?,
    pokemonDetailViewModel: PokemonDetailViewModel = hiltViewModel()
) {
    pokemonDetailViewModel.getDetail(pokemonName)

    when (val viewState = pokemonDetailViewModel.stateFlow.collectAsState().value) {
        is ViewState.Success -> {
            viewState.data?.let { PokemonDetailCard(pokemonDetailModel = it) }
        }
    }
}


@Composable
fun PokemonDetailCard(pokemonDetailModel: PokemonDetailModel) {


    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(
                bottom = 6.dp,
                top = 6.dp,
            )
            .wrapContentWidth()
            .wrapContentHeight(),
        elevation = 8.dp,
    ) {
        Column(
            modifier = Modifier
                .wrapContentWidth()
                .wrapContentHeight()
                .padding(top = 12.dp, bottom = 12.dp, start = 8.dp, end = 8.dp)
        ) {
            val image =
                loadPicture(
                    url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/" + pokemonDetailModel.id + ".png",
                    defaultImage = R.drawable.pokeball_background
                ).value
            image?.let { img ->
                Image(
                    bitmap = img.asImageBitmap(),
                    contentDescription = "Recipe Featured Image",
                    modifier = Modifier
                        .wrapContentWidth()
                        .wrapContentHeight(),
                    contentScale = ContentScale.Crop,
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp, bottom = 12.dp, start = 8.dp, end = 8.dp)
                ) {
                    pokemonDetailModel.name?.let {
                        Text(
                            text = it.uppercase(Locale.ENGLISH),
                            modifier = Modifier
                                .fillMaxWidth(0.85f)
                                .wrapContentWidth(Alignment.Start),
                            style = MaterialTheme.typography.h4,
                        )
                    }

                    pokemonDetailModel.weight.let {
                        Text(
                            text = "Weight : $it",
                            modifier = Modifier
                                .fillMaxWidth(0.85f)
                                .wrapContentWidth(Alignment.Start),
                            style = MaterialTheme.typography.h5,
                        )
                    }

                    pokemonDetailModel.height.let {
                        Text(
                            text = "Height : $it",
                            modifier = Modifier
                                .fillMaxWidth(0.85f)
                                .wrapContentWidth(Alignment.Start),
                            style = MaterialTheme.typography.h5,
                        )
                    }

                    pokemonDetailModel.stats.let {
                        Text(
                            text = "STATS",
                            modifier = Modifier
                                .fillMaxWidth(0.85f)
                                .padding(top = 10.dp)
                                .wrapContentWidth(Alignment.Start),
                            style = MaterialTheme.typography.h4,
                        )

                        LazyRow {
                            itemsIndexed(items = it) { index, item ->
                                item.stat?.name?.let { it1 ->
                                    Text(
                                        text = it1,
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .wrapContentWidth()
                                            .wrapContentHeight(),
                                        style = MaterialTheme.typography.h5,
                                    )
                                }
                            }
                        }
                    }

                    pokemonDetailModel.types.let {
                        Text(
                            text = "TYPES",
                            modifier = Modifier
                                .fillMaxWidth(0.85f)
                                .padding(top = 10.dp)
                                .wrapContentWidth(Alignment.Start),
                            style = MaterialTheme.typography.h4,
                        )

                        LazyRow {
                            itemsIndexed(items = it) { index, item ->
                                item.type?.name?.let { it1 ->
                                    Text(
                                        text = it1,
                                        modifier = Modifier
                                            .padding(8.dp)
                                            .wrapContentWidth()
                                            .wrapContentHeight(),
                                        style = MaterialTheme.typography.h5,
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}