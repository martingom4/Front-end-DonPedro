package com.example.donpedro.iu.screen


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.donpedro.navigation.AppScreens

import com.example.donpedro.ui.theme.PrimaryRed
import com.example.donpedro.ui.theme.SecondarySalmon
import com.example.donpedro.ui.theme.TertiaryCream
import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import androidx.compose.foundation.background
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.example.donpedro.data.local.SessionManager
import com.example.donpedro.viewmodel.SessionViewModel


@Composable
fun OnBoardingScreen(navController: NavController) {
    val context = LocalContext.current
    val sessionViewModel = remember { SessionViewModel(SessionManager(context)) }
    val isLoggedIn = sessionViewModel.isLoggedIn


    val bgGradient = Brush.verticalGradient(
        colors = listOf(TertiaryCream, Color.White),
        tileMode = TileMode.Clamp
    )

    Scaffold(
        containerColor = Color.Transparent,
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(bgGradient)
                .padding(padding)
        ) {
            if(isLoggedIn == false ){
                BodyContent(navController)
            }
        }
    }
}

@Composable
fun BodyContent(navController: NavController) {
    val buttonShape = RoundedCornerShape(32.dp)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // App name
        Text(
            text = "DON PEDRO",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Bold,
                color = PrimaryRed
            ),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Tagline
        Text(
            text = "Comida hecha con el alma",
            style = MaterialTheme.typography.bodyLarge.copy(color = SecondarySalmon),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Primary action: Sign Up
        Button(
            onClick = { navController.navigate(route = AppScreens.RegisterScreen.route) },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = buttonShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryRed,
                contentColor = Color.White
            )
        ) {
            Text(text = "Registrarse")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Secondary action: Login
        OutlinedButton(
            onClick = { navController.navigate(route = AppScreens.LoginScreen.route) },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = buttonShape,
            border = BorderStroke(1.dp, PrimaryRed),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = PrimaryRed
            )
        ) {
            Text(text = "Iniciar sesión")
        }
    }
}


