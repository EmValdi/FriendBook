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
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import friendbook.composeapp.generated.resources.Res
import friendbook.composeapp.generated.resources.person_icon
import friendbook.composeapp.generated.resources.left_arrow
import friendbook.composeapp.generated.resources.check

/**
 * Editable Account screen (frontend only).
 * - Title bar with Back and Save buttons (no Icon() dependency)
 * - Avatar placeholder
 * - Text fields: Email / Password(new) / User name
 * - onSave returns: email, newPassword (null if unchanged), userName
 */
@Composable
fun AccountEditScreen(
    initialEmail: String = "rintaro.sato@student.junia.com",
    initialUserName: String = "Rintaro",
    onBack: () -> Unit = {},
    onSave: (email: String, newPassword: String?, userName: String) -> Unit = { _, _, _ -> },
) {
    var email by rememberSaveable { mutableStateOf(initialEmail) }
    var password by rememberSaveable { mutableStateOf("") } // empty = unchanged
    var userName by rememberSaveable { mutableStateOf(initialUserName) }
    var showPassword by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            EditTopBar(
                title = "Edit your account",
                onBack = onBack,
                onSave = { onSave(email.trim(), password.ifBlank { null }, userName.trim()) }
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

            // --- Fields ---
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password (new)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val text = if (showPassword) "Hide" else "Show"
                    TextButton(onClick = { showPassword = !showPassword }) { Text(text) }
                }
            )

            OutlinedTextField(
                value = userName,
                onValueChange = { userName = it },
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
    onBack: () -> Unit,
    onSave: () -> Unit
) {
    Surface(tonalElevation = 3.dp) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // --- Back button ---
            IconButton(onClick = onBack) {
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
                modifier = Modifier.weight(1f)
            )

            // --- Save button ---
            IconButton(onClick = onSave) {
                Image(
                    painter = painterResource(Res.drawable.check),
                    contentDescription = "Save",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}