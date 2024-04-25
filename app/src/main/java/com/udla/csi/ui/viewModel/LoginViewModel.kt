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
    //private val _loading = MutableLiveData(false)

    private val _correo = MutableLiveData<String>()
    val correo: MutableLiveData<String>
        get() = _correo

    private val _contra = MutableLiveData<String>()
    val contra: MutableLiveData<String>
        get() = _contra

    private val _contravisible = MutableLiveData<Boolean>()
    val contravisible: MutableLiveData<Boolean>
        get() = _contravisible

    private val _loginEnabled = MutableLiveData<Boolean>()
    val loginEnabled: MutableLiveData<Boolean>
        get() = _loginEnabled

    fun onContraVisibilityChange(visible: Boolean) {
        _contravisible.value = visible
    }

    fun onLoginChange(correo: String, contra: String) {
        _correo.value = correo
        _contra.value = contra
        _loginEnabled.value = correovalido(correo) && contravalida(contra)
    }

    private fun contravalida(contra: String): Boolean = contra.length >= 8

    private fun correovalido(correo: String): Boolean = Patterns.EMAIL_ADDRESS.matcher(correo).matches()
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

}