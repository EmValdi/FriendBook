package org.junia.friendbook.uiAboutAccount.screens.account

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme.colors
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
import kotlinx.coroutines.launch
import org.junia.friendbook.data.SessionData
import org.junia.friendbook.data.interfaces.getAuthPlatform
import org.junia.friendbook.ui.views.BottomNavBar
import org.junia.friendbook.ui.views.FriendbookScreen

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
                navController,
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
            ReadonlyField(label = "Email", value = SessionData.userMail)
            ReadonlyField(label = "Username", value = SessionData.userName)

            Spacer(Modifier.height(12.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    navController.navigate(FriendbookScreen.Login.name)
                    val auth = getAuthPlatform()
                    kotlinx.coroutines.MainScope().launch {
                        auth.logout()
                    }

                }
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
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.background,
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
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
            IconButton(onClick = {
                navController.navigate(FriendbookScreen.EditAccount.name)
            }) {
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
    Card (
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        border = BorderStroke(1.dp, color=MaterialTheme.colorScheme.onBackground)
    ){
        Column(
            Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 14.dp)
        ) {
            Text(label,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(Modifier.height(6.dp))
            Text(
                value,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}