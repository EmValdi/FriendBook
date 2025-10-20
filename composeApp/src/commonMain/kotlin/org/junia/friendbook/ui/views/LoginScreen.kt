package org.junia.friendbook.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import org.junia.friendbook.ui.Strings
import org.junia.friendbook.ui.viewmodels.LoginViewModel
import org.junia.friendbook.ui.viewmodels.SignupViewModel

@Composable
fun LoginScreen (
    navController: NavHostController,
    modifier: Modifier = Modifier,
    loginViewmodel: LoginViewModel = viewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val loginStatus = loginViewmodel.loginResult

    LaunchedEffect(loginStatus) {
        when (loginStatus) {
            "Success" -> {
                snackbarHostState.showSnackbar(
                    "Successful Login!",
                    duration = SnackbarDuration.Short)
                loginViewmodel.clearLoginResult()
                navController.navigate(FriendbookScreen.Friendlist.name)
            }
            null -> Unit
            else -> {
                snackbarHostState.showSnackbar(loginStatus)
                loginViewmodel.clearLoginResult()
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                snackbar = {data ->
                    Snackbar(
                        snackbarData = data,
                        containerColor = colorScheme.primary,
                        contentColor = colorScheme.onPrimary
                    )
                }) }
    ){
        Column(modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            Spacer(modifier = Modifier.height(150.dp))
            Text(
                "Login",
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(40.dp))
            LoginFormulary(
                email = loginViewmodel.email,
                password = loginViewmodel.password,
                emailError = loginViewmodel.emailError,
                passwordError = loginViewmodel.passwordError,
                onEmailChanged = {loginViewmodel.onEmailChanged(it)},
                onPasswordChanged = {loginViewmodel.onPasswordChanged(it)}
            )
            Spacer(modifier = Modifier.height(100.dp))
            Button(
                onClick = { loginViewmodel.onLoginClicked()},
                modifier = Modifier
                    .width(150.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text("Log in now")
            }
            Spacer(modifier = Modifier.height(100.dp))
            Text(
                text = "Don't have an account? Sign in for free!",
                color = colorScheme.primary,
                modifier = Modifier.clickable {
                    navController.navigate(FriendbookScreen.Signup.name)
                }
            )
        }
    }
}

@Composable
fun LoginFormulary(
    email: String,
    password: String,
    emailError: String?,
    passwordError: String?,
    onEmailChanged: (String) -> Unit,
    onPasswordChanged: (String) -> Unit
){
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding()
    ) {
        TextField(
            value = email,
            onValueChange = onEmailChanged,
            singleLine = true,
            shape = shapes.large,
            modifier = Modifier.width(350.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = colorScheme.secondary,
                unfocusedContainerColor = colorScheme.background,
                disabledContainerColor = colorScheme.surface,
                focusedTextColor = colorScheme.onBackground,
                unfocusedTextColor = colorScheme.onBackground
            ),
            label = {
                Text(
                    text = Strings.emailLabel,
                    color = colorScheme.onBackground)
            },
            placeholder = {
                Text(text = Strings.emailLabel)
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Done
            )
        )
        if (emailError != null) {
            Text(
                text = emailError,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        TextField(
            value = password,
            onValueChange = onPasswordChanged,
            singleLine = true,
            shape = shapes.large,
            modifier = Modifier.width(350.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = colorScheme.secondary,
                unfocusedContainerColor = colorScheme.background,
                disabledContainerColor = colorScheme.surface,
                focusedTextColor = colorScheme.onBackground,
                unfocusedTextColor = colorScheme.onBackground
            ),
            label = {
                Text(
                    text = Strings.passwordLabel,
                    color = colorScheme.onBackground
                )
            },
            placeholder = {
                Text(text = Strings.passwordLabel)
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        )
        if (passwordError != null) {
            Text(
                text = passwordError,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}