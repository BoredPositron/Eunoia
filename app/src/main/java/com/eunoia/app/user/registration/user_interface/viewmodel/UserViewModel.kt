package com.eunoia.app.user.registration.user_interface.viewmodel

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eunoia.app.user.registration.domain.UserRepository
import com.eunoia.app.utils.Resource
import com.eunoia.app.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.multibindings.IntKey
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository
): ViewModel(){
    private val _userFlow = MutableStateFlow<Response<Boolean>?>(null)
    val userFlow: StateFlow<Response<Boolean>?> = _userFlow

    fun register(age: String, gender: String) = viewModelScope.launch{
        _userFlow.value = Response.Loading
        val result = repository.register( age, gender)
        _userFlow.value = result
    }
}