package com.udla.csi.ui.screen

import com.udla.csi.ui.theme.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.udla.csi.Navigation.Screens

import com.udla.csi.R
import com.udla.csi.ui.viewModel.LoginViewModel


@Composable
fun LoginScreen (viewModel: LoginViewModel,navController: NavController){
    Box(Modifier.background(color = fondo)){
        Box(
            Modifier
                .fillMaxSize()
                .padding(20.dp)){
            Login(Modifier.align(Alignment.Center), viewModel,navController)
        }
    }

}


@Composable
fun Login(modifier: Modifier, viewModel: LoginViewModel,navController: NavController) {
    val correo : String by viewModel.correo.observeAsState(initial = "")
    val contra : String by viewModel.contra.observeAsState(initial = "")
    val loginEneable : Boolean by viewModel.loginEnabled.observeAsState(initial = false)
    val contravisible : Boolean by viewModel.contravisible.observeAsState(initial = false)

    Box(modifier = modifier.fillMaxSize()){
        Row (Modifier.align(Alignment.TopCenter)){
            Column {
                Text(text ="Iniciar sesión", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF000000),modifier = Modifier
                    .clickable {}
                    .padding(10.dp))
            }
            Column {
                Text(text = "Crear cuenta", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF000000), modifier = Modifier
                    .clickable {}
                    .padding(10.dp))
            }
        }
        Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            HeaderImage(Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier.padding(10.dp))
            CampoCorreo(correo) { viewModel.onLoginChange(it, contra) }
            Spacer(modifier = Modifier.padding(5.dp))
            CampoContra(contra,viewModel,contravisible) { viewModel.onLoginChange(correo, it) }
            Spacer(modifier = Modifier.padding(15.dp))
            BotonInicio(Modifier.align(Alignment.CenterHorizontally),loginEneable,viewModel,correo,contra,navController)
        }
        OlvContra(Modifier.align(Alignment.BottomCenter))
    }

}

@Composable
fun BotonInicio(modifier: Modifier,loginEneable: Boolean,viewModel: LoginViewModel,correo:String,contra:String,navController: NavController) {
    Button(
        onClick = {viewModel.iniciosesioncorreo(correo,contra){navController.navigate(Screens.HomeSceen.name)}},
        modifier = modifier
            .width(200.dp)
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE1E3DA)),
        enabled = loginEneable
    ) {
        Text(text = "Iniciar sesión", color = Color(0xFF000000))

    }
}


@Composable
fun OlvContra(modifier: Modifier) {
    Text(
        text = "¿Olvidaste la contraseña?",
        modifier = modifier.clickable {},
        fontSize = 12.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF666057)
    )
}

@Composable
fun CampoContra(contra:String,  viewModel: LoginViewModel,contravisible: Boolean , onTextFieldChanged: (String) -> Unit) {

    val transformacionvisual = if (viewModel.contravisible.value == true){
        VisualTransformation.None
    } else {
        PasswordVisualTransformation()
    }

    OutlinedTextField(value = contra,
        onValueChange = { onTextFieldChanged(it)},
        Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Contraseña")} ,
        visualTransformation = transformacionvisual,
        keyboardOptions = KeyboardOptions(keyboardType =  KeyboardType.Password),
        singleLine = true,
        trailingIcon = { Contravisibleicono(viewModel,contravisible) },
        maxLines = 1)
}

@Composable
fun Contravisibleicono(viewModel: LoginViewModel, contravisible: Boolean) {
    val image =
        if (viewModel.contravisible.value == true){
            Icons.Default.VisibilityOff
        } else {
            Icons.Default.Visibility
        }
    IconButton(onClick = {viewModel.onContraVisibilityChange(!contravisible) }){
        Icon(imageVector = image, contentDescription ="a" )
        
    }
}

@Composable
fun CampoCorreo(correo:String , onTextFieldChanged: (String) -> Unit) {
    OutlinedTextField(value = correo,
        onValueChange = {onTextFieldChanged(it)},
        Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Correo")} ,
        keyboardOptions = KeyboardOptions(keyboardType =  KeyboardType.Email),
        singleLine = true,
        maxLines = 1)
}

@Composable
fun HeaderImage(modifier: Modifier) {
    Image(painter = painterResource(id = R.drawable.salud ), contentDescription = "Header" ,modifier=modifier)
}
