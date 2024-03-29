package dev.jorgealejandro.sdet.code_challenge.ui.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.jorgealejandro.sdet.code_challenge.dto.UserDto
import dev.jorgealejandro.sdet.code_challenge.ui.events.LoginEvent
import dev.jorgealejandro.sdet.code_challenge.ui.models.LoginViewModel
import dev.jorgealejandro.sdet.code_challenge.ui.theme.CodechallengeTheme

@Composable
fun LoginComponent() {
    var user by remember { mutableStateOf(UserDto()) }
    val context = LocalContext.current
    val viewModel: LoginViewModel = LoginViewModel()

    fun doLogin() {
        if (user.email.isEmpty() || user.password.isEmpty()) {
            Toast.makeText(context, "Complete both fields.", Toast.LENGTH_LONG).show()
        } else {
            viewModel.onUIEvent(LoginEvent.DoLogin(user))
        }
    }

    val state = viewModel.uiState
    if (state.isError) {
        Toast.makeText(context, "Wrong credentials", Toast.LENGTH_SHORT).show()
    } else if (state.isSuccess) {
        Toast.makeText(context, "Welcome!", Toast.LENGTH_SHORT).show()
    } else if (state.isLoading) {
        Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show()
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        LoginEmailField(
            value = "email",
            onValueChange = {
                content -> user = user.copy(email = content.trim())
            },
            modifier = Modifier.fillMaxWidth()
        )
        LoginPasswordField(
            value = "password",
            onValueChange = {
                content -> user = user.copy(password = content.trim())
            },
            submit = { doLogin() },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { doLogin() },
            enabled = true,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Login")
        }
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun LoginComponentPreview() {
    CodechallengeTheme {
        LoginComponent()
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun LoginFormPreviewDark() {
    CodechallengeTheme(darkTheme = true) {
        LoginComponent()
    }
}