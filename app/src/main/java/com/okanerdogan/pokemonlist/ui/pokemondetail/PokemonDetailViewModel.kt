package com.okanerdogan.pokemonlist.ui.pokemondetail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okanerdogan.pokemonlist.data.PokemonDetailModel
import com.okanerdogan.pokemonlist.repository.PokemonDetailRepository
import com.okanerdogan.pokemonlist.util.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel
@Inject
constructor(
    private val pokemonDetailRepo: PokemonDetailRepository,
) : ViewModel() {

    private var pokemonDetail: PokemonDetailModel? = null
    private val _stateFlow = MutableStateFlow<ViewState>(ViewState.Loading)

    var scrollPosition = 0

    val stateFlow: StateFlow<ViewState>
        get() = _stateFlow

    fun getDetail(name: String?) {
        viewModelScope.launch {
            try {
                pokemonDetail = name?.let { pokemonDetailRepo.getDetail(name = it) }
                _stateFlow.value = pokemonDetail?.let { ViewState.Success(it) }!!
            } catch (ex: UnknownHostException) {
                Log.e("LOG", "Internet connection lost", ex)
            }

        }
    }
}