package com.example.redehoteleira.domain.useCase.minHotel

import com.example.redehoteleira.domain.models.Hotel
import com.example.redehoteleira.domain.models.TotalSum
import com.example.redehoteleira.domain.useCase.base.NoParamsUseCase


class SelectHotelUseCase : NoParamsUseCase<Pair<Hotel, Int>>() {
    override suspend fun execute(params: Unit): Pair<Hotel, Int> {
        val minValue = minOf(TotalSum.TremBao.total, TotalSum.UaiSo.total, TotalSum.BaoDemais.total)

        val minValueHotel =
            if (TotalSum.TremBao.total == TotalSum.UaiSo.total && TotalSum.TremBao.total == minValue) {
                if (Hotel.TremBao.rating > Hotel.UaiSo.rating) Hotel.TremBao
                else Hotel.UaiSo
            } else if (TotalSum.TremBao.total == TotalSum.BaoDemais.total && TotalSum.TremBao.total == minValue) {
                if (Hotel.TremBao.rating > Hotel.BaoDemais.rating) Hotel.TremBao
                else Hotel.BaoDemais
            } else if (TotalSum.UaiSo.total == TotalSum.BaoDemais.total && TotalSum.UaiSo.total == minValue) {
                if (Hotel.UaiSo.rating > Hotel.BaoDemais.rating) Hotel.UaiSo
                else Hotel.BaoDemais
            } else {
                when {
                    TotalSum.TremBao.total == minValue -> Hotel.TremBao
                    TotalSum.UaiSo.total == minValue -> Hotel.UaiSo
                    else -> Hotel.BaoDemais
                }
            }
        TotalSum.TremBao.total = 0
        TotalSum.UaiSo.total = 0
        TotalSum.BaoDemais.total = 0

        return Pair(minValueHotel, minValue)
    }
}