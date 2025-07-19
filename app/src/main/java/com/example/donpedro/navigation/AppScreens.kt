package com.example.donpedro.navigation

sealed class AppScreens (val route: String){
    object OnboardingScreen: AppScreens("onboarding_screen")
    object RegisterScreen: AppScreens("register_screen")
    object LoginScreen: AppScreens("login_screen")

    object HomeScreen: AppScreens("home_screen")

    object ProfileScreen: AppScreens("profile_screen")
}