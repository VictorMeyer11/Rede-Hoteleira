package com.example.redehoteleira.navigation

sealed class Screen(val route: String) {
    object FirstScreen : Screen(route = "first_screen")
    object SelectClientTypeScreen : Screen(route = "clientType_screen")
    object StayPeriodScreen : Screen(route = "stay_screen/{clientType}") {
        fun buildRoute(clientType: String) = "stay_screen/$clientType"
    }
    object MapScreen : Screen(route = "map_screen/{firstDateText}/{secondDateText}/{clientType}") {
        fun buildRoute(firstDateText: String, secondDateText: String, clientType: String) =
            "map_screen/$firstDateText/$secondDateText/$clientType"
    }
}