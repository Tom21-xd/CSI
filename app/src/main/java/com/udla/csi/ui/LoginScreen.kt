package com.udla.csi.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.udla.csi.R


@Composable
fun LoginScreen (){
    Box(Modifier.background(color = Color(0xFF92C5FC))){
        Box(
            Modifier
                .fillMaxSize()
                .padding(20.dp)){
            Login(Modifier.align(Alignment.Center))
        }
    }

}

@Composable
fun Login(modifier: Modifier) {
    Column(modifier) {
        Row {

        }
        HeaderImage(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(35.dp))
        CampoCorreo()
        Spacer(modifier = Modifier.padding(5.dp))
        CampoContra()
        Spacer(modifier = Modifier.padding(15.dp))
        BotonInicio(Modifier.align(Alignment.CenterHorizontally))
        Spacer(modifier = Modifier.padding(15.dp))
        OlvContra(Modifier.align(Alignment.CenterHorizontally))
    }
}

@Composable
fun BotonInicio(modifier: Modifier) {
    Button(
        onClick = {},
        modifier = modifier
            .width(200.dp)
            .height(48.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE1E3DA))


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
fun CampoContra() {
    val contraState = remember { mutableStateOf(TextFieldValue()) }
    OutlinedTextField(value = contraState.value,
        onValueChange = {contraState.value = it},
        Modifier.fillMaxWidth(),
        placeholder = { Text(text = "Contraseña")} ,
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType =  KeyboardType.Password),
        singleLine = true,
        maxLines = 1)
}

@Composable
fun CampoCorreo() {
    val correoState = remember { mutableStateOf(TextFieldValue()) }
    OutlinedTextField(value = correoState.value,
        onValueChange = {correoState.value = it},
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
