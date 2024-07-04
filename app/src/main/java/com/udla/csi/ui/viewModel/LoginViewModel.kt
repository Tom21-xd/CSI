package com.udla.csi.ui.viewModel

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val  auth :FirebaseAuth =Firebase.auth
    private val _loading = MutableLiveData(false)

    private val _correo = MutableLiveData<String>() //Se usa para crear e iniciar sesion
    val correo: MutableLiveData<String>
        get() = _correo

    private val _contra = MutableLiveData<String>()//Se usa para crear e iniciar sesion
    val contra: MutableLiveData<String>
        get() = _contra

    private val _log_regis = MutableLiveData<Boolean>()
    val log_regis: MutableLiveData<Boolean>
        get() = _log_regis

    private val _contravisible = MutableLiveData<Boolean>()
    val contravisible: MutableLiveData<Boolean>
        get() = _contravisible

    private val _loginEnabled = MutableLiveData<Boolean>()
    val loginEnabled: MutableLiveData<Boolean>
        get() = _loginEnabled

    fun onContraVisibilityChange(visible: Boolean) {
        _contravisible.value = visible
    }

    private fun contravalida(contra: String): Boolean = contra.length >= 8

    private fun correovalido(correo: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(correo).matches()

    fun onLoginChange(correo: String, contra: String) {
        _correo.value = correo
        _contra.value = contra
        _loginEnabled.value = correovalido(correo) && contravalida(contra)
    }

    fun iniciosesioncorreo(correo:String,contrasenia:String,HomeScreen:() ->Unit)
    = viewModelScope.launch {
        try {
            auth.signInWithEmailAndPassword(correo,contrasenia)
            .addOnCompleteListener{
                task->
                if (task.isSuccessful){
                    Log.d("AppDengue","Ha iniciado sesion")
                    HomeScreen()
                }else{
                    Log.d("AppDengue","No ha iniciado sesion")
                }

            }
        }catch (e:Exception){
            Log.d("AppDengue","Error al iniciar sesion")
        }
    }

    //---------------------------Registro----------------------------------------

    fun OnRegisterChange(correo: String, contra: String, confirmacionContra: String, nombres: String, apellidos: String, departamento: String, ciudad: String, direccion: String, personalMedico: Boolean) {
        _correo.value = correo
        _contra.value = contra
        _confirmacionContra.value = confirmacionContra
        _nombres.value = nombres
        _apellidos.value = apellidos
        _departamento.value = departamento
        _ciudad.value = ciudad
        _direccion.value = direccion
        _personalMedico.value = personalMedico
    }

    fun registroUsuario(correo:String,contrasenia:String,HomeScreen:() ->Unit){
        if(_loading.value== false){
            _loading.value =true
            auth.createUserWithEmailAndPassword(correo,contrasenia)
                .addOnCompleteListener{
                        task->
                    if (task.isSuccessful){
                        HomeScreen()
                    }else{
                        Log.d("AppDengue","Error al registrar")
                    }
                }
            _loading.value =false
        }
    }

    private val _nombres = MutableLiveData<String>()
    val nombres: MutableLiveData<String>
        get() = _nombres

    private val _apellidos = MutableLiveData<String>()
    val apellidos: MutableLiveData<String>
        get() = _apellidos

    private val _departamento = MutableLiveData<String>()
    val departamento: MutableLiveData<String>
        get() = _departamento

    private val _ciudad = MutableLiveData<String>()
    val ciudad: MutableLiveData<String>
        get() = _ciudad

    private val _direccion = MutableLiveData<String>()
    val direccion: MutableLiveData<String>
        get() = _direccion

    private val _personalMedico = MutableLiveData<Boolean>()
    val personalMedico: MutableLiveData<Boolean>
        get() = _personalMedico

    private val _confirmacionContra = MutableLiveData<String>()
    val confirmacionContra: MutableLiveData<String>
        get() = _confirmacionContra

}