package com.udla.csi.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.udla.csi.ui.screen.*
import com.udla.csi.ui.viewModel.LoginViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = Screens.LoginScreen.name) {

        composable(Screens.LoginScreen.name) {
            LoginScreen(viewModel = LoginViewModel(), navController = navController)
        }
        composable(Screens.HomeSceen.name) {
            HomeScreen(navController = navController)
        }
    }
}