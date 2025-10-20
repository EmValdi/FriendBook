package org.junia.friendbook.uiAboutAccount.screens.account

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import friendbook.composeapp.generated.resources.Res
import friendbook.composeapp.generated.resources.person_icon
import friendbook.composeapp.generated.resources.pencil

/**
 * Account screen (frontend only, no Firebase connection yet)
 *
 * Structure:
 *  - Custom top bar with title + edit button
 *  - Avatar placeholder
 *  - Read-only account info blocks (Email, Password, Username)
 *  - Logout button
 */
@Composable
fun AccountScreen(
    email: String = "rintaro.sato@student.junia.com",
    userName: String = "Rintaro",
    onClickEdit: () -> Unit = {},
    onClickLogout: () -> Unit = {}
) {
    Scaffold(
        topBar = { SimpleTopBar(title = "Account", onClickEdit = onClickEdit) }
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

            // --- Read-only fields ---
            ReadonlyField(label = "Email", value = email)
            ReadonlyField(label = "Password", value = "********")
            ReadonlyField(label = "Username", value = userName)

            Spacer(Modifier.height(12.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onClickLogout
            ) { Text("Logout") }
        }
    }
}

/**
 * Custom top bar to replace TopAppBar (stable API only).
 * - Title at the left
 * - Edit button at the right
 */
@Composable
private fun SimpleTopBar(
    title: String,
    onClickEdit: () -> Unit
) {
    Surface(tonalElevation = 3.dp) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
            IconButton(onClick = onClickEdit) {
                Image(
                    painter = painterResource(Res.drawable.pencil),
                    contentDescription = "Edit account",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

/** Small reusable component for displaying read-only info. */
@Composable
private fun ReadonlyField(label: String, value: String) {
    Card {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp)
        ) {
            Text(label, style = MaterialTheme.typography.labelMedium)
            Spacer(Modifier.height(6.dp))
            Text(
                value,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
            )
        }
    }
}