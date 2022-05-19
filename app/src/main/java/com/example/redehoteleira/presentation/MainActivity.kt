package com.example.redehoteleira.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import com.example.redehoteleira.navigation.Navigation
import com.example.redehoteleira.presentation.theme.RedeHoteleiraTheme


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RedeHoteleiraTheme {
                Navigation()
            }
        }
    }
}