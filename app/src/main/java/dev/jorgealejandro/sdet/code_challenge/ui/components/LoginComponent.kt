package dev.jorgealejandro.sdet.code_challenge.ui.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
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
fun LoginComponent(
    model: LoginViewModel? = null
) {
    var user by remember { mutableStateOf(UserDto()) }
    val context = LocalContext.current

    fun doLogin() {
        if (user.email.isEmpty() || user.password.isEmpty()) {
            Toast.makeText(context, "Complete both fields.", Toast.LENGTH_LONG).show()
        } else {
            model?.onUIEvent(LoginEvent.DoLogin(user))
        }
    }

    fun doLogout() {
        model?.onUIEvent(LoginEvent.DoLogout)
    }

    val state = model?.uiState
    if (state?.isError == true) {
        Toast.makeText(context, "Wrong credentials", Toast.LENGTH_SHORT).show()
        model.onUIEvent(LoginEvent.OnErrorDispatched)
    } else if (state?.isLoginSuccess == true) {
        Toast.makeText(context, "Welcome!", Toast.LENGTH_SHORT).show()
        val message = "Welcome ${state.userDto?.email}!"
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            Text(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterHorizontally),
                text = message
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { doLogout() },
                enabled = true,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Log out")
            }
        }

        model.onUIEvent(LoginEvent.OnSuccessDispatched)
    } else if (state?.isLoading == true) {
        Toast.makeText(context, "Loading...", Toast.LENGTH_SHORT).show()
        model.onUIEvent(LoginEvent.OnLoadingDispatched)
    } else {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp)
        ) {
            LoginEmailField(
                value = user.email,
                onValueChange = { content ->
                    user = user.copy(email = content.trim())
                },
                modifier = Modifier.fillMaxWidth()
            )
            LoginPasswordField(
                value = user.password,
                onValueChange = { content ->
                    user = user.copy(password = content.trim())
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