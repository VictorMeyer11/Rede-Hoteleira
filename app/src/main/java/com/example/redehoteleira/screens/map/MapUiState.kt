package com.example.redehoteleira.screens.map

import com.example.redehoteleira.domain.models.CoordinatesUi
import com.example.redehoteleira.domain.models.Hotel


data class MapUiState(
    val bestHotelAndValue: Pair<Hotel, Int>?,
    val coordinatesUi: List<CoordinatesUi>?
)