package com.example.redehoteleira.screens.stayPeriod

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.redehoteleira.R
import kotlinx.coroutines.launch


@Composable
fun StayPeriodScreen(
    viewModel: StayPeriodViewModel,
    navigateToNext: (firstDateText: String, secondDateText: String, clientType: String) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    val datePickerDialog = uiState.datePicker
    val spacerSize = 30.dp

    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = Color.White
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Qual o período de estadia?", fontSize = 28.sp)
            Spacer(modifier = Modifier.size(spacerSize))
            Text("De:", fontSize = 23.sp)
            Spacer(modifier = Modifier.size(spacerSize))
            Button(
                onClick = {
                    viewModel.firstButtonClicked()
                    datePickerDialog?.show()
                },
                modifier = Modifier
                    .height(50.dp)
                    .width(200.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                border = BorderStroke(width = 1.dp, color = Color.Black)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        modifier = Modifier.size(27.dp),
                        backgroundColor = Color.White,
                        elevation = 0.dp
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.calendar_icon),
                            contentDescription = "calendar_icon"
                        )
                    }
                    Spacer(Modifier.width(15.dp))
                    Text(uiState.firstDateText.replace("-", "/"), fontSize = 15.sp)
                }
            }
            Spacer(modifier = Modifier.size(spacerSize))
            Text("Até:", fontSize = 23.sp)
            Spacer(modifier = Modifier.size(spacerSize))
            Button(
                onClick = {
                    viewModel.secondButtonClicked()
                    datePickerDialog?.show()
                },
                modifier = Modifier
                    .height(50.dp)
                    .width(200.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                border = BorderStroke(width = 1.dp, color = Color.Black)
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        modifier = Modifier.size(27.dp),
                        backgroundColor = Color.White,
                        elevation = 0.dp
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.calendar_icon),
                            contentDescription = "calendar icon"
                        )
                    }
                    Spacer(Modifier.width(15.dp))
                    Text(uiState.secondDateText.replace("-", "/"), fontSize = 15.sp)
                }
            }
            Spacer(modifier = Modifier.size(spacerSize))
            Button(
                onClick = {
                    if (uiState.shouldButtonActivate) {
                        navigateToNext(
                            uiState.firstDateText,
                            uiState.secondDateText,
                            uiState.clientType
                        )
                    } else {
                        coroutineScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar("Preencha todas as datas!")
                        }
                    }
                },
                modifier = Modifier
                    .height(50.dp)
                    .width(200.dp),
                colors = if (uiState.shouldButtonActivate) ButtonDefaults.buttonColors(
                    backgroundColor = Color.Black
                )
                else ButtonDefaults.buttonColors(backgroundColor = Color.LightGray),
            ) {
                var textColor = Color.Black
                if (uiState.shouldButtonActivate) textColor = Color.White
                Text("Encontrar hotéis", color = textColor, fontSize = 15.sp)
            }
        }
    }
}
