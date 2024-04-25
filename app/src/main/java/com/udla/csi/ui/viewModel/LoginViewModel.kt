package com.udla.csi.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel(){
    private val  auth :FirebaseAuth =Firebase.auth
    private val _loading = MutableLiveData(false)



}