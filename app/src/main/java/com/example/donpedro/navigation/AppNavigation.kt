package com.example.donpedro.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.donpedro.data.local.SessionManager
import com.example.donpedro.data.remote.RetrofitInstance
import com.example.donpedro.iu.screen.EditProfileScreen
import com.example.donpedro.iu.screen.HomeScreen
import com.example.donpedro.iu.screen.LoginScreen
import com.example.donpedro.iu.screen.OnBoardingScreen
import com.example.donpedro.iu.screen.ProfileScreen
import com.example.donpedro.iu.screen.RegisterScreen
import com.example.donpedro.repository.ClientRepository
import com.example.donpedro.viewmodel.EditProfileViewModel
import com.example.donpedro.viewmodel.EditProfileViewModelFactory
import com.example.donpedro.viewmodel.HomeViewModel
import com.example.donpedro.viewmodel.HomeViewModelFactory
import com.example.donpedro.viewmodel.LoginViewModel
import com.example.donpedro.viewmodel.LoginViewModelFactory
import com.example.donpedro.viewmodel.ProfileViewModel
import com.example.donpedro.viewmodel.RegisterViewModel
import com.example.donpedro.viewmodel.RegisterViewModelFactory

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val sessionManager = remember {SessionManager(context)}

   val accessToken by sessionManager.getAccessToken().collectAsState(initial = null)
    val isLoggedIn = accessToken != null

    val startDestination = if(isLoggedIn){
        AppScreens.HomeScreen.route
    }else{
        AppScreens.OnboardingScreen.route
    }
    val repository = ClientRepository(RetrofitInstance.api, sessionManager)
    val viewModelRegister: RegisterViewModel = viewModel(factory = RegisterViewModelFactory(sessionManager, repository))
    val viewModelLogin: LoginViewModel = viewModel(factory = LoginViewModelFactory(sessionManager,repository))
    val viewModelProfile: EditProfileViewModel = viewModel( factory = EditProfileViewModelFactory(sessionManager, repository))
    val viewModelHome: HomeViewModel = viewModel(factory = HomeViewModelFactory(repository))

    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = AppScreens.OnboardingScreen.route)  {
            OnBoardingScreen(navController)
        }
        composable(route = AppScreens.RegisterScreen.route) {
            RegisterScreen(navController, viewModelRegister)
        }
        composable(route = AppScreens.LoginScreen.route) {
            LoginScreen(navController, viewModelLogin)
        }

        composable(route = AppScreens.HomeScreen.route){
            HomeScreen(navController, viewModelHome )
        }

        composable(route = AppScreens.ProfileScreen.route) {
            ProfileScreen(navController)
        }
        composable(route = AppScreens.UpdateProfileScreen.route) {
            EditProfileScreen(navController, viewModelProfile)
        }
    }
}