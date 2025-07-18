package com.example.donpedro.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.donpedro.data.ClientRepository
import com.example.donpedro.data.remote.RetrofitInstance
import com.example.donpedro.iu.screen.LoginScreen
import com.example.donpedro.iu.screen.OnBoardingScreen
import com.example.donpedro.iu.screen.RegisterScreen
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
            val repository = ClientRepository(RetrofitInstance.api)
            val viewModel: RegisterViewModel = viewModel(factory = RegisterViewModelFactory(repository))
            RegisterScreen(navController, viewModel)
        }
        composable(route = AppScreens.LoginScreen.route) {
            LoginScreen(navController)
        }
    }
}