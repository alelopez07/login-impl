package dev.jorgealejandro.sdet.code_challenge

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import dev.jorgealejandro.sdet.code_challenge.ui.components.LoginComponent
import dev.jorgealejandro.sdet.code_challenge.ui.models.LoginViewModel
import dev.jorgealejandro.sdet.code_challenge.ui.theme.CodechallengeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel: LoginViewModel = LoginViewModel()
        setContent {
            CodechallengeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginComponent(
                        model = viewModel
                    )
                }
            }
        }
    }
}