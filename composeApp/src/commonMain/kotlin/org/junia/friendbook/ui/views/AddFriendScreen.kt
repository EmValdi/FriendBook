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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
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
fun AddFriendScreen(
    navController: NavHostController,
    addFriendViewModel: AddfriendViewModel = viewModel()
) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Add Friend", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = {navController.navigate(FriendbookScreen.Friendlist.name)}) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = {
                        addFriendViewModel.addFriend(navController)
                    }) {
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
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 24.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
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

            // Campos editables con el mismo estilo
            InfoInputField(
                label = "Name",
                value = addFriendViewModel.name
            ) { addFriendViewModel.onNameChange(it) }

            InfoInputField(
                label = "Country",
                value = addFriendViewModel.country
            ) { addFriendViewModel.onCountryChange(it) }

            InfoInputField(
                label = "Phone number",
                value = addFriendViewModel.phoneNumber
            ) { addFriendViewModel.onPhoneNumberChange(it) }

            InfoInputField(
                label = "Instagram Account",
                value = addFriendViewModel.instagram
            ) { addFriendViewModel.onInstagramChange(it) }

            InfoInputField(
                label = "School Name",
                value = addFriendViewModel.school
            ) { addFriendViewModel.onSchoolChange(it) }

            InfoInputField(
                label = "Hobbies",
                value = addFriendViewModel.hobbies.joinToString(", ")
            ) { addFriendViewModel.onHobbiesChange(it) }

            Spacer(Modifier.height(32.dp))
        }
    }
}

@Composable
fun InfoInputField(label: String, value: String, onValueChange: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(label, color = Color.Gray, fontSize = 14.sp)
        TextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp, color = Color.Black),
            /*colors = TextFieldDefaults.colors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),*/
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Divider(modifier = Modifier.padding(top = 8.dp))
    }
}