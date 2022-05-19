package com.example.redehoteleira.screens.selectClientType

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun SelectClientTypeScreen(navigateToNext: (String) -> Unit) {
    val spacerSize = 30.dp

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Vamos começar!\nVocê é...", fontSize = 28.sp, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.size(spacerSize))
        Button(
            onClick = { navigateToNext("padrão") },
            modifier = Modifier
                .height(120.dp)
                .width(240.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black)
        ) {
            Text("Cliente padrão", color = Color.White, fontSize = 23.sp)
        }
        Spacer(modifier = Modifier.size(spacerSize))
        Button(
            onClick = { navigateToNext("fidelidade") },
            modifier = Modifier
                .height(120.dp)
                .width(240.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black)
        ) {
            Text(
                "Cliente fidelidade",
                color = Color.White,
                fontSize = 23.sp,
                textAlign = TextAlign.Center
            )
        }
        Spacer(modifier = Modifier.size(spacerSize))
        Button(
            onClick = { navigateToNext("especial") },
            modifier = Modifier
                .height(120.dp)
                .width(240.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black)
        ) {
            Text("Cliente especial", color = Color.White, fontSize = 23.sp)
        }
    }
}