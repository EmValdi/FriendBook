package org.junia.friendbook.uiAboutAccount.screens.account

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.jetbrains.compose.resources.painterResource
import friendbook.composeapp.generated.resources.Res
import friendbook.composeapp.generated.resources.person_icon
import friendbook.composeapp.generated.resources.pencil
import org.junia.friendbook.ui.views.BottomNavBar

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
    navController: NavHostController,
    modifier: Modifier = Modifier,
    email: String = "rintaro.sato@student.junia.com",
    userName: String = "Rintaro",
    onClickEdit: () -> Unit = {},
    onClickLogout: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            // Pasamos el modifier con statusBarsPadding aquí
            SimpleTopBar(
                title = "Account",
                onClickEdit = onClickEdit,
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding() // ✅ esto añadirá el padding superior necesario
            )
        },
        bottomBar = {
            BottomNavBar(navController)
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()              // primero ocupar todo el espacio disponible
                .padding(innerPadding)     // luego aplicar el padding que deja el Scaffold (topBar + bottomBar)
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
 * - Ahora usa el modifier pasado desde arriba (IMPORTANTE)
 */
@Composable
private fun SimpleTopBar(
    title: String,
    onClickEdit: () -> Unit,
    modifier: Modifier = Modifier // valor por defecto
) {
    // Aplica el modifier recibido (que incluye statusBarsPadding desde el caller)
    Surface(
        modifier = modifier,
        tonalElevation = 3.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp) // altura de la barra de herramientas (contenido)
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
private fun ReadonlyField(
    label: String,
    value: String
) {
    Card {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 14.dp)
        ) {
            Text(label,
                style = MaterialTheme.typography.labelMedium
            )
            Spacer(Modifier.height(6.dp))
            Text(
                value,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
            )
        }
    }
}