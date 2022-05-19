package com.example.redehoteleira.domain.models

import com.google.android.gms.maps.model.LatLng


sealed class Hotel(
    val name: String,
    val rating: Int,
    val weekdaysPrice: Map<Type, Int>,
    val weekendDaysPrice: Map<Type, Int>,
    val coordinates: Coordinates
) {
    enum class Type {
        NORMAL,
        FIDELITY,
        SPECIAL
    }
    enum class Coordinates(val value: LatLng) {
        TREM_BAO(value = LatLng(-19.936348595738075, -43.93331506348796)),
        UAI_SO(value = LatLng(-19.936585127931924, -43.935298625771935)),
        BAO_DEMAIS(value = LatLng(-19.937760095143854, -43.934535434774475))
    }

    object TremBao : Hotel(
        name = "Trem Bão",
        rating = 3,
        weekdaysPrice = mapOf(Type.NORMAL to 110, Type.FIDELITY to 80, Type.SPECIAL to 50),
        weekendDaysPrice = mapOf(Type.NORMAL to 90, Type.FIDELITY to 80, Type.SPECIAL to 50),
        coordinates = Coordinates.TREM_BAO
    )
    object UaiSo : Hotel(
        name = "Uai Sô",
        rating = 4,
        weekdaysPrice = mapOf(Type.NORMAL to 160, Type.FIDELITY to 110, Type.SPECIAL to 80),
        weekendDaysPrice = mapOf(Type.NORMAL to 60, Type.FIDELITY to 50, Type.SPECIAL to 20),
        coordinates = Coordinates.UAI_SO
    )
    object BaoDemais : Hotel(
        name = "Bão Demais",
        rating = 5,
        weekdaysPrice = mapOf(Type.NORMAL to 220, Type.FIDELITY to 100, Type.SPECIAL to 70),
        weekendDaysPrice = mapOf(Type.NORMAL to 150, Type.FIDELITY to 40, Type.SPECIAL to 10),
        coordinates = Coordinates.BAO_DEMAIS
    )
}