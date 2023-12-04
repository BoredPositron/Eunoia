package com.eunoia.app.user.registration.user_interface.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eunoia.app.user.registration.data.model.User
import com.eunoia.app.user.registration.domain.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: UserRepository
): ViewModel() {
    private val _dataFlow = MutableStateFlow<List<User>?>(null)
    val dataFlow: StateFlow<List<User>?> = _dataFlow

    init{
        if (_dataFlow.value == null){
            getUsers()

        }
    }
    fun getUsers() = viewModelScope.launch{
        _dataFlow.value = repository.getUserMatches()
    }

}