package com.example.donpedro.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.donpedro.data.local.SessionManager
import com.example.donpedro.data.remote.RetrofitInstance
import com.example.donpedro.iu.screen.HomeScreen
import com.example.donpedro.iu.screen.LoginScreen
import com.example.donpedro.iu.screen.OnBoardingScreen
import com.example.donpedro.iu.screen.RegisterScreen
import com.example.donpedro.repository.ClientRepository
import com.example.donpedro.viewmodel.RegisterViewModel
import com.example.donpedro.viewmodel.RegisterViewModelFactory

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.OnboardingScreen.route) {
        composable(route = AppScreens.OnboardingScreen.route)  {
            OnBoardingScreen(navController)
        }
        composable(route = AppScreens.RegisterScreen.route) {
            val context = LocalContext.current
            val sessionManager = remember { SessionManager(context) }
            val repository = ClientRepository(RetrofitInstance.api, sessionManager)
            val viewModel: RegisterViewModel = viewModel(factory = RegisterViewModelFactory(sessionManager,repository ))
            RegisterScreen(navController, viewModel)
        }
        composable(route = AppScreens.LoginScreen.route) {
            LoginScreen(navController)
        }

        composable(route = AppScreens.HomeScreen.route){
            HomeScreen(navController)
        }
    }
}