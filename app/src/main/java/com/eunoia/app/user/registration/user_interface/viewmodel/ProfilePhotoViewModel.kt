package com.eunoia.app.user.registration.user_interface.viewmodel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eunoia.app.user.registration.domain.UserRepository
import com.eunoia.app.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfilePhotoViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {
    private val _pictureFlow = MutableStateFlow<Response<Boolean>?>(null)
    val pictureFlow: StateFlow<Response<Boolean>?> = _pictureFlow

    fun updateProfilePhoto(imageUri: Uri) = viewModelScope.launch {
        _pictureFlow.value = Response.Loading
        val result = repository.addProfilePhoto(imageUri)
        _pictureFlow.value = result
    }
}