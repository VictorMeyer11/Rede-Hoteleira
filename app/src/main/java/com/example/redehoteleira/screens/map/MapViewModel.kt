package com.example.redehoteleira.screens.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.redehoteleira.domain.models.CoordinatesUi
import com.example.redehoteleira.domain.models.Hotel
import com.example.redehoteleira.domain.useCase.map.MarkerAlphaValueUseCase
import com.example.redehoteleira.domain.useCase.minHotel.MinValueHotelUseCase
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class MapViewModel(
    private val firstDateText: String,
    private val secondDateText: String,
    private val clientType: String,
    private val minValueHotelUseCase: MinValueHotelUseCase,
    private val markerAlphaValueUseCase: MarkerAlphaValueUseCase
) : ViewModel() {
    private val _uiState: MutableStateFlow<MapUiState> = MutableStateFlow(
        MapUiState(
            bestHotelAndValue = null,
            coordinatesUi = null
        )
    )
    val uiState: StateFlow<MapUiState> = _uiState

    init {
        viewModelScope.launch {
            delay(1000)
            getMinValueHotel()
            initializeCoordinates()
        }
    }

    private suspend fun initializeCoordinates() {
        _uiState.update { mapState ->
            mapState.bestHotelAndValue?.first?.let {
                val coordinatesUi = markerAlphaValueUseCase(it.coordinates)
                mapState.copy(
                    coordinatesUi = coordinatesUi
                )
            } ?: mapState
        }
    }

    private suspend fun getMinValueHotel() {
        _uiState.update {
            it.copy(
                bestHotelAndValue = minValueHotelUseCase(
                    params = MinValueHotelUseCase.Params(
                        firstDateText = firstDateText,
                        secondDateText = secondDateText,
                        clientType = clientType
                    )
                )
            )
        }
    }
}