package com.example.donpedro.iu.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.donpedro.data.local.SessionManager
import com.example.donpedro.data.remote.RetrofitInstance
import com.example.donpedro.ui.theme.PrimaryRed
import com.example.donpedro.ui.theme.SecondarySalmon
import com.example.donpedro.ui.theme.TertiaryCream
import com.example.donpedro.iu.components.TopBarComponent
import com.example.donpedro.navigation.AppScreens
import com.example.donpedro.repository.ClientRepository
import com.example.donpedro.viewmodel.ProfileViewModel
import com.example.donpedro.viewmodel.ProfileViewModelFactory

@Composable
fun ProfileOption(title: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .clickable { /* Navigate or handle action */ },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, fontSize = 16.sp)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "›", color = Color.Gray)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController) {
    val bgGradient = Brush.verticalGradient(
        colors = listOf(TertiaryCream, Color.White)
    )
    val context = LocalContext.current
    val sessionManager = remember { SessionManager(context) }
    val repository = ClientRepository(api = RetrofitInstance.api, sessionManager = sessionManager)
    val viewModel: ProfileViewModel = viewModel(factory = ProfileViewModelFactory(sessionManager, repository))
    val userName by sessionManager.getUserName().collectAsState(initial = null)
    val userEmail by sessionManager.getUserEmail().collectAsState(initial = null)
    val userId by sessionManager.getUserId().collectAsState(initial = null)


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bgGradient)
    ) {
        Scaffold(
            containerColor = Color.Transparent,

            bottomBar = { BottomBar(navController) },


        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            ) {
                Spacer(modifier = Modifier.height(24.dp))


                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("$userName", fontWeight = androidx.compose.ui.text.font.FontWeight.Bold, fontSize = 20.sp)
                    Text("$userEmail", color = Color.Gray, fontSize = 18.sp)
                    Spacer(modifier = Modifier.height(2.dp))

                    TextButton(
                        onClick = { navController.navigate(AppScreens.UpdateProfileScreen.route) }
                    ) {
                        Text(
                            text = "✏️ Editar",
                            color = SecondarySalmon,
                            fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold,
                            fontSize = 14.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                Text("Cuenta", fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold, fontSize = 16.sp, color = PrimaryRed)
                Spacer(modifier = Modifier.height(8.dp))
                ProfileOption("Método de Pago")
                ProfileOption("Mi Carrito")
                ProfileOption("Ayuda y Soporte")

                Spacer(modifier = Modifier.height(24.dp))

                Text("Más Información", fontWeight = androidx.compose.ui.text.font.FontWeight.SemiBold, fontSize = 16.sp, color = PrimaryRed)
                Spacer(modifier = Modifier.height(8.dp))
                ProfileOption("Política de Privacidad")
                ProfileOption("Calificar App")

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "Cerrar sesión",
                    color = Color.Red,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                        .clickable {
                            viewModel.logout(
                                onSuccess = {
                                    navController.navigate("login") {
                                        popUpTo("profile") { inclusive = true }
                                    }
                                },
                                onError = {
                                    // Handle error, e.g., show a Snackbar or Toast
                                    println("Error al cerrar sesión: $it")
                                }
                            )
                        }
                )
            }
        }
    }
}