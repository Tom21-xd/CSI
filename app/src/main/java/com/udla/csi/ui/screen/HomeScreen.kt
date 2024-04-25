package com.udla.csi.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.udla.csi.ui.theme.fondo

@Composable
fun HomeScreen(navController: NavController) {
    Box (modifier = Modifier.fillMaxSize().background(fondo), contentAlignment = Alignment.Center){
        Text(text ="Home Screen")
    }
}
