package com.example.donpedro.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.donpedro.iu.screen.LoginScreen
import com.example.donpedro.iu.screen.OnBoardingScreen
import com.example.donpedro.iu.screen.RegisterScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.OnboardingScreen.route) {
        composable(route = AppScreens.OnboardingScreen.route)  {
            // OnBoardingScreen(navController)
            OnBoardingScreen(navController)
        }
        composable(route = AppScreens.RegisterScreen.route) {
            RegisterScreen(navController)
        }
        composable(route = AppScreens.LoginScreen.route) {
            LoginScreen(navController)
        }
    }

}