package com.example.redehoteleira.domain.useCase.map

import com.example.redehoteleira.domain.models.CoordinatesUi
import com.example.redehoteleira.domain.models.Hotel
import com.example.redehoteleira.domain.useCase.base.UseCase


class MarkerAlphaValueUseCase : UseCase<Hotel.Coordinates, List<CoordinatesUi>>() {
    override suspend fun execute(params: Hotel.Coordinates): List<CoordinatesUi> {
        return listOf(
            getCoordinatesUi(Hotel.TremBao, params),
            getCoordinatesUi(Hotel.UaiSo, params),
            getCoordinatesUi(Hotel.BaoDemais, params),
        )
    }

    private fun getCoordinatesUi(hotel: Hotel, highlightedCoordinates: Hotel.Coordinates) =
        CoordinatesUi(
            hotel = hotel,
            alpha = if (hotel.coordinates == highlightedCoordinates) 1f
            else 0.5f
        )
}