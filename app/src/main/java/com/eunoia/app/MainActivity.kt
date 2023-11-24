package com.eunoia.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.eunoia.app.authentication.user_interface.AuthViewModel
import com.eunoia.app.navigation.AppNavHost
import com.eunoia.app.ui.theme.EunoiaTheme
import com.eunoia.app.user.user_interface.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val authViewModel by viewModels<AuthViewModel>()
    private val userViewModel by viewModels<UserViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EunoiaTheme {
                AppNavHost(authViewModel, userViewModel)
            }
        }
    }
}

