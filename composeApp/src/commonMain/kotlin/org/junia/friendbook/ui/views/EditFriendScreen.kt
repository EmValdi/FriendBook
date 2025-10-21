package org.junia.friendbook.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import friendbook.composeapp.generated.resources.Res
import friendbook.composeapp.generated.resources.nopicture
import org.jetbrains.compose.resources.painterResource
import org.junia.friendbook.ui.viewmodels.AddfriendViewModel
import org.junia.friendbook.ui.viewmodels.FriendslistViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditFriendScreen(
    navController: NavHostController,
    friendsViewModel: FriendslistViewModel
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Friend", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(FriendbookScreen.Friendlist.name) }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { friendsViewModel.editFriend(navController) }) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Save",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Column scrollable con los campos
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp, vertical = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(Modifier.height(24.dp))

                // Avatar
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(Color.LightGray),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.nopicture),
                        contentDescription = "Avatar",
                        modifier = Modifier.size(40.dp),
                        tint = Color.White
                    )
                }

                Spacer(Modifier.height(24.dp))

                InfoInputField("Name", friendsViewModel.name) { friendsViewModel.onNameChange(it) }
                InfoInputField("Country", friendsViewModel.country) { friendsViewModel.onCountryChange(it) }
                InfoInputField("Phone number", friendsViewModel.phoneNumber) { friendsViewModel.onPhoneNumberChange(it) }
                InfoInputField("Instagram Account", friendsViewModel.instagram) { friendsViewModel.onInstagramChange(it) }
                InfoInputField("School Name", friendsViewModel.school) { friendsViewModel.onSchoolChange(it) }
                InfoInputField("Hobbies", friendsViewModel.hobbies.joinToString(", ")) { friendsViewModel.onHobbiesChange(it) }

                Spacer(Modifier.height(100.dp))
            }

            Button(
                onClick = {
                    friendsViewModel.deleteFriend(navController)
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.BottomCenter)
            ) {
                Text("Delete Friend", color = Color.White)
            }
        }
    }
}