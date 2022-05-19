package com.example.redehoteleira.domain.models

sealed class TotalSum(
    var total: Int = 0
) {
    object TremBao : TotalSum()
    object UaiSo : TotalSum()
    object BaoDemais : TotalSum()
}