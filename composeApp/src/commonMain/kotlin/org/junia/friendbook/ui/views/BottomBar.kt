package org.junia.friendbook.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import org.junia.friendbook.ui.Strings
import androidx.compose.material3.Icon
import org.jetbrains.compose.resources.painterResource
import friendbook.composeapp.generated.resources.Res
import friendbook.composeapp.generated.resources.list
import friendbook.composeapp.generated.resources.information
import friendbook.composeapp.generated.resources.nopicture
import friendbook.composeapp.generated.resources.paintpalette

@Composable
fun BottomNavBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background
    ) {
        // --- Friendlist ---
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(Res.drawable.list),
                    contentDescription = "Friendlist"
                )
            },
            label = {
                Text(
                    "Friendlist",
                    color = MaterialTheme.colorScheme.onBackground
                )
            },
            selected = navController.currentDestination?.route == FriendbookScreen.Friendlist.name,
            onClick = {
                navController.navigate(FriendbookScreen.Friendlist.name) {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            }
        )

        // --- Hobbies ---
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(Res.drawable.paintpalette),
                    contentDescription = "Hobbies"
                )
            },
            label = {
                Text(
                    "Hobbies",
                    color = MaterialTheme.colorScheme.onBackground
                )
            },
            selected = navController.currentDestination?.route == FriendbookScreen.Hobbylist.name,
            onClick = {
                navController.navigate(FriendbookScreen.Hobbylist.name) {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            }
        )

        // --- About ---
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(Res.drawable.information),
                    contentDescription = "About"
                )
            },
            label = {
                Text(
                    "About",
                    color = MaterialTheme.colorScheme.onBackground
                )
            },
            selected = navController.currentDestination?.route == FriendbookScreen.About.name,
            onClick = {
                navController.navigate(FriendbookScreen.About.name) {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            }
        )

        // --- Account ---
        NavigationBarItem(
            icon = {
                Icon(
                    painter = painterResource(Res.drawable.nopicture),
                    contentDescription = "Account"
                )
            },
            label = {
                Text(
                    "Account",
                    color = MaterialTheme.colorScheme.onBackground
                )
            },
            selected = navController.currentDestination?.route == FriendbookScreen.Account.name,
            onClick = {
                navController.navigate(FriendbookScreen.Account.name) {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            }
        )
    }
}