package org.junia.friendbook.ui.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import org.junia.friendbook.ui.Strings
import org.junia.friendbook.ui.viewmodels.FriendslistViewModel
import org.junia.friendbook.ui.viewmodels.LoginViewModel

@Composable
fun FriendlistScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    friendslistViewmodel: FriendslistViewModel = viewModel()
){
    Scaffold(
        topBar = {
            FriendlistBar(

            )
        }
    ) {}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FriendlistBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                Strings.friendlistTop,
                color = MaterialTheme.colorScheme.tertiary
            )
                },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        modifier = modifier
            .statusBarsPadding()
    )
}