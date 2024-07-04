package com.udla.csi.ui.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.udla.csi.ui.screen.*
import com.udla.csi.ui.viewModel.LoginViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController,startDestination = Screens.LoginScreen.name) {

        composable(Screens.LoginScreen.name ) {
            LoginScreen(viewModel = LoginViewModel(), navController = navController)
        }
        composable(Screens.HomeScreen.name) {
            HomeScreen(navController = navController)
        }


    }
}
