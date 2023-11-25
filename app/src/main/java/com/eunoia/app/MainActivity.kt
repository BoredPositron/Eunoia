package com.eunoia.app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.eunoia.app.authentication.user_interface.AuthViewModel
import com.eunoia.app.navigation.AppNavHost
import com.eunoia.app.ui.theme.EunoiaTheme
import com.eunoia.app.user.user_interface.viewmodel.ProfilePhotoViewModel
import com.eunoia.app.user.user_interface.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val authViewModel by viewModels<AuthViewModel>()
    private val userViewModel by viewModels<UserViewModel>()
    private val photoViewModel by viewModels<ProfilePhotoViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EunoiaTheme {
                AppNavHost(authViewModel, userViewModel, photoViewModel)
            }
        }
    }
}

