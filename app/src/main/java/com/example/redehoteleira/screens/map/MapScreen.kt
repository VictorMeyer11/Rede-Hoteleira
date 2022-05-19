package com.example.redehoteleira.screens.map

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.redehoteleira.domain.models.Hotel
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*


private const val LAT = -19.937760095143854
private const val LONG = -43.934535434774475
private const val ZOOM = 16f

@Composable
fun MapScreen(viewModel: MapViewModel) {
    val uiState by viewModel.uiState.collectAsState()
    val scaffoldState = rememberScaffoldState()
    val coordinates = uiState.coordinatesUi

    coordinates?.let {
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(LatLng(LAT, LONG), ZOOM)
        }

        Scaffold(scaffoldState = scaffoldState) {
            Column(modifier = Modifier.fillMaxSize()) {
                GoogleMap(
                    modifier = Modifier
                        .fillMaxHeight(0.9f)
                        .fillMaxWidth(),
                    cameraPositionState = cameraPositionState
                ) {
                    coordinates.forEach { coordinateUi ->
                        Marker(
                            position = coordinateUi.hotel.coordinates.value,
                            title = coordinateUi.hotel.name,
                            snippet = "Nota: ${coordinateUi.hotel.rating}",
                            alpha = coordinateUi.alpha
                        )
                    }
                }
                uiState.bestHotelAndValue?.let {
                    Text(text = "O valor da estadia será: R$ ${it.second},00", fontSize = 23.sp)
                }
            }
        }
    } ?: Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(modifier = Modifier.size(60.dp), color = Color.White)
    }
}