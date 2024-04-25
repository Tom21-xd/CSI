package com.udla.csi.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.udla.csi.ui.theme.fondo
import com.udla.csi.ui.viewModel.LoginViewModel

@Composable
fun RegistroScreen (viewModel: LoginViewModel, navController: NavController){
    Box(Modifier.background(color = fondo)){
        Box(
            Modifier
                .fillMaxSize()
                .padding(20.dp)){
            Registro(Modifier.align(Alignment.Center), viewModel,navController)
        }
    }

}

@Composable
fun Registro(modifier: Modifier, viewModel: LoginViewModel, navController: NavController) {
    Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Row {

        }
    }
}
