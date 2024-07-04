package com.udla.csi.ui.screen

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavController
import com.udla.csi.ui.Navigation.Screens
import com.udla.csi.R
import com.udla.csi.ui.viewModel.*

@Composable
fun LoginScreen (viewModel: LoginViewModel,navController: NavController){
    Box(Modifier.background(color = fondo)){
        Box(
            Modifier
                .fillMaxSize()
                .padding(20.dp)){
            Row (Modifier.align(Alignment.TopCenter)){
                Column (Modifier.align(Alignment.CenterVertically)) {
                    Text(text ="Iniciar sesión", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF000000),modifier = Modifier
                        .clickable { viewModel.log_regis.value = false }
                        .padding(10.dp))
                    Divider(color = Color(0xFF000000), thickness = 2.dp, modifier = Modifier.fillMaxWidth(0.37f))
                }
                Spacer(modifier = Modifier.padding(25.dp))
                Column {
                    Text(text = "Crear cuenta", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color(0xFF000000), modifier = Modifier
                        .clickable { viewModel.log_regis.value = true }
                        .padding(10.dp))
                    Divider(color = Color(0xFF000000), thickness = 2.dp, modifier = Modifier.fillMaxWidth(0.75f))

                }

            }


            val logRegis : Boolean by viewModel.log_regis.observeAsState(initial = false)
            AnimatedVisibility(visible = !logRegis) {
                Login(Modifier.align(Alignment.Center), viewModel, navController)
            }
            AnimatedVisibility(visible = logRegis) {
                Registro(Modifier.align(Alignment.Center), viewModel, navController)
            }
        }


    }

}

@Composable
fun Login(modifier: Modifier ,viewModel: LoginViewModel,navController: NavController) {

    val correo : String by viewModel.correo.observeAsState(initial = "")
    val contra : String by viewModel.contra.observeAsState(initial = "")
    val loginEneable : Boolean by viewModel.loginEnabled.observeAsState(initial = false)
    val contravisible : Boolean by viewModel.contravisible.observeAsState(initial = false)

    Box(modifier = modifier.fillMaxSize()){

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
        onClick = {viewModel.iniciosesioncorreo(correo,contra){navController.navigate(Screens.HomeScreen.name)}},
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

//------------------------------------------------Registro------------------------------------------------------------

@Composable
fun Registro(modifier: Modifier, viewModel:LoginViewModel, navController: NavController) {
    val nombres : String by viewModel.nombres.observeAsState(initial = "")
    val correo : String by viewModel.correo.observeAsState(initial = "")
    val contra : String by viewModel.contra.observeAsState(initial = "")
    val confirmacionContra : String by viewModel.confirmacionContra.observeAsState(initial = "")
    val apellidos : String by viewModel.apellidos.observeAsState(initial = "")
    val departamento : String by viewModel.departamento.observeAsState(initial = "")
    val ciudad : String by viewModel.ciudad.observeAsState(initial = "")
    val direccion : String by viewModel.direccion.observeAsState(initial = "")
    val personalMedico : Boolean by viewModel.personalMedico.observeAsState(initial = false)

    Box(modifier = modifier.fillMaxSize()){
        Column(modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "¿Como te llamas?", fontSize = 25.sp, fontWeight = FontWeight.Bold, color = Color(0xFF000000), modifier = Modifier
                .clickable { viewModel.log_regis.value = true })

            Spacer(modifier = Modifier.padding(10.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.weight(1f)) {
                    Campo(nombres,"Nombres") { viewModel.OnRegisterChange(correo, contra, confirmacionContra, it, apellidos, departamento, ciudad, direccion, personalMedico) }
                }
                Spacer(modifier = Modifier.padding(5.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Campo(apellidos,"Apellidos") { viewModel.OnRegisterChange(correo, contra, confirmacionContra, nombres, it, departamento, ciudad, direccion, personalMedico) }
                }
            }
            Row (modifier){
                desplegable()
            }
        }
    }

}

@Composable
fun Campo(dato: String,nombre:String, onTextFieldChanged: (String) -> Unit) {
    OutlinedTextField(value = dato,
        onValueChange = { onTextFieldChanged(it)},
        Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType =  KeyboardType.Text),
        singleLine = true,
        label = { Text(nombre) },
        maxLines = 1)
}

@Composable
fun desplegable(){
    var expanded by remember { mutableStateOf(false) }
    val opciones = listOf("Opción 1", "Opción 2", "Opción 3")
    var seleccionado by remember { mutableStateOf(opciones[0]) }

    Column {
        OutlinedTextField(
            value = seleccionado,
            onValueChange = { seleccionado = it },
            label = { Text("Departamento") },
            modifier = Modifier.clickable { expanded = true }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth(0.8f),
            properties = PopupProperties(focusable = false)
        ) {
            opciones.forEach { opcion ->
                DropdownMenuItem(
                    text = { Text(opcion) },
                    onClick = {
                        seleccionado = opcion
                        expanded = false
                    }
                )
            }
        }
    }
}