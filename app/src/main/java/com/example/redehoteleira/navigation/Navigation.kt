package com.example.redehoteleira.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.redehoteleira.domain.useCase.map.MarkerAlphaValueUseCase
import com.example.redehoteleira.domain.useCase.minHotel.MinValueHotelUseCase
import com.example.redehoteleira.domain.useCase.minHotel.SelectHotelUseCase
import com.example.redehoteleira.screens.map.MapScreen
import com.example.redehoteleira.screens.map.MapViewModel
import com.example.redehoteleira.screens.selectClientType.SelectClientTypeScreen
import com.example.redehoteleira.screens.splash.SplashScreen
import com.example.redehoteleira.screens.stayPeriod.StayPeriodScreen
import com.example.redehoteleira.screens.stayPeriod.StayPeriodViewModel
import java.util.*


@Composable
fun Navigation() {
    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = Screen.FirstScreen.route
    ) {
        composable(route = Screen.FirstScreen.route) {
            SplashScreen { navController.navigate(Screen.SelectClientTypeScreen.route) }
        }
        composable(route = Screen.SelectClientTypeScreen.route) {
            SelectClientTypeScreen { navController.navigate(Screen.StayPeriodScreen.buildRoute(it)) }
        }
        composable(
            route = Screen.StayPeriodScreen.route,
            arguments = listOf(
                navArgument("clientType") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("clientType")?.let {
                val stayPeriodViewModel = StayPeriodViewModel(clientType = it)
                stayPeriodViewModel.getDatePickerDialog(context = context)
                StayPeriodScreen(stayPeriodViewModel) { firstDateText, secondDateText, clientType ->
                    navController.navigate(
                        Screen.MapScreen.buildRoute(
                            firstDateText = firstDateText,
                            secondDateText = secondDateText,
                            clientType = clientType
                        )
                    )
                }
            }
        }
        composable(
            route = Screen.MapScreen.route,
            arguments = listOf(
                navArgument("firstDateText") { type = NavType.StringType },
                navArgument("secondDateText") { type = NavType.StringType },
                navArgument("clientType") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            backStackEntry.arguments?.let {
                val firstDateText = it.getString("firstDateText") ?: ""
                val secondDateText = it.getString("secondDateText") ?: ""
                val clientType = it.getString("clientType") ?: ""

                val mapViewModel = MapViewModel(
                    firstDateText = firstDateText,
                    secondDateText = secondDateText,
                    clientType = clientType,
                    minValueHotelUseCase = MinValueHotelUseCase(
                        selectHotelUseCase = SelectHotelUseCase(),
                        calendar = Calendar.getInstance()
                    ),
                    markerAlphaValueUseCase = MarkerAlphaValueUseCase()
                )
                MapScreen(mapViewModel)
            }
        }
    }
}