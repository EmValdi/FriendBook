package org.junia.friendbook.uiAboutAccount.screens.account

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import org.jetbrains.compose.resources.painterResource
import friendbook.composeapp.generated.resources.Res
import friendbook.composeapp.generated.resources.person_icon
import friendbook.composeapp.generated.resources.left_arrow
import friendbook.composeapp.generated.resources.check
import org.junia.friendbook.data.SessionData
import org.junia.friendbook.ui.viewmodels.EditAccountViewModel
import org.junia.friendbook.ui.views.FriendbookScreen

/**
 * Editable Account screen (frontend only).
 * - Title bar with Back and Save buttons (no Icon() dependency)
 * - Avatar placeholder
 * - Text fields: Email / Password(new) / User name
 * - onSave returns: email, newPassword (null if unchanged), userName
 */
@Composable
fun AccountEditScreen(
    navController: NavHostController,
    initialEmail: String = SessionData.userMail,
    initialUserName: String = "",
    editAccountViewModel: EditAccountViewModel = viewModel()
) {

    Scaffold(
        topBar = {
            EditTopBar(
                title = "Edit your account",
                editAccountViewModel,
                navController
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Spacer(Modifier.height(8.dp))

            // --- Avatar placeholder ---
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Surface(
                    modifier = Modifier
                        .size(84.dp)
                        .clip(CircleShape),
                    tonalElevation = 3.dp,
                    shape = CircleShape
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Image(
                            painter = painterResource(Res.drawable.person_icon),
                            contentDescription = "Profile icon",
                            modifier = Modifier.size(48.dp)
                        )
                    }
                }
            }

            OutlinedTextField(
                value = editAccountViewModel.password,
                onValueChange = { editAccountViewModel.password = it },
                label = { Text("Password (new)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                visualTransformation = if (editAccountViewModel.showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val text = if (editAccountViewModel.showPassword) "Hide" else "Show"
                    TextButton(onClick = { editAccountViewModel.showPassword = !editAccountViewModel.showPassword }) { Text(text) }
                }
            )

            OutlinedTextField(
                value = editAccountViewModel.userName,
                onValueChange = { editAccountViewModel.userName = it },
                label = { Text("User name") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
        }
    }
}

/** Minimal, stable custom top bar with back and save actions. */
@Composable
private fun EditTopBar(
    title: String,
    editAccountViewModel: EditAccountViewModel,
    navController: NavHostController
) {
    Surface(
        tonalElevation = 3.dp,
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = WindowInsets.statusBars.asPaddingValues().calculateTopPadding()) // evita empalme con status bar
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // --- Back button ---
            IconButton(
                onClick = {
                    navController.navigate(FriendbookScreen.Account.name)
                }
            ) {
                Image(
                    painter = painterResource(Res.drawable.left_arrow),
                    contentDescription = "Back",
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(Modifier.width(8.dp))

            // --- Title ---
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp), // evita que el texto se amontone con los botones
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            // --- Save button ---
            IconButton(onClick = {
                when {
                    editAccountViewModel.password.isNotBlank() && editAccountViewModel.userName.isNotBlank() -> {
                        editAccountViewModel.changePassword(navController)
                        editAccountViewModel.changeUserName(navController)
                    }
                    editAccountViewModel.password.isNotBlank() -> {
                        editAccountViewModel.changePassword(navController)
                    }
                    editAccountViewModel.userName.isNotBlank() -> {
                        editAccountViewModel.changeUserName(navController)
                    }

                }
            }) {
                Image(
                    painter = painterResource(Res.drawable.check),
                    contentDescription = "Save",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}