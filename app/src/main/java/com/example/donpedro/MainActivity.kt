package com.example.donpedro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.donpedro.navigation.AppNavigation
import com.example.donpedro.ui.theme.DonPedroTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DonPedroTheme {
                AppNavigation()
            }
        }
    }
}