package com.example.donpedro.iu.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.donpedro.ui.theme.PrimaryRed
import com.example.donpedro.ui.theme.TertiaryCream
import com.example.donpedro.viewmodel.EditProfileViewModel
import com.example.donpedro.iu.components.TopBarComponent
import com.example.donpedro.navigation.AppScreens

@Composable
fun EditProfileScreen(
    navController: NavController,
    viewModel: EditProfileViewModel
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val uiMsg by remember { viewModel.uiMessage }

    // Muestra snackbar cuando hay mensaje
    LaunchedEffect(uiMsg) {
        uiMsg?.let { snackbarHostState.showSnackbar(it) }
    }

    val bgGradient = Brush.verticalGradient(
        colors = listOf(TertiaryCream, Color.White)
    )

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        containerColor = Color.Transparent,
        topBar = {
            TopBarComponent(
                title = "Login",
                onBackClick = { navController.navigate(route = AppScreens.ProfileScreen.route) }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(bgGradient)
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {

            OutlinedTextField(
                value = viewModel.name,
                onValueChange = { viewModel.name = it },
                label = { Text("Nombre") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = viewModel.email,
                onValueChange = { viewModel.email = it },
                label = { Text("Correo") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { viewModel.saveChanges { navController.popBackStack() } },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryRed)
            ) {
                Text("Guardar", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}