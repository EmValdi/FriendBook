package org.junia.friendbook.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.border
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import friendbook.composeapp.generated.resources.Res
import friendbook.composeapp.generated.resources.nopicture
import org.jetbrains.compose.resources.painterResource
import org.junia.friendbook.data.interfaces.friend
import org.junia.friendbook.ui.viewmodels.FriendslistViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FriendDetailScreen(
    navController: NavHostController,
    friendslistViewmodel: FriendslistViewModel
) {
    var friend = friendslistViewmodel.currentDetail
    println(friendslistViewmodel.currentDetail.id)

    if (friend == null) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Name", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(FriendbookScreen.Friendlist.name)
                        friendslistViewmodel.currentDetail = friend("")
                    }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = {navController.navigate(FriendbookScreen.Editfriend.name)}) {
                        Icon(Icons.Default.Edit, contentDescription = "Edit")
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

            InfoField(label = "Name", value = friend.name)
            InfoField(label = "Country", value = friend.country)
            InfoField(label = "Phone number", value = friend.phone_number)
            InfoField(label = "Instagram Account", value = friend.instagram)
            InfoField(label = "School Name", value = friend.school)
            InfoField(label = "Hobbies", value = friend.hobbies.joinToString(", "))
        }
    }
}

@Composable
fun InfoField(label: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(label, color = Color.Gray, fontSize = 14.sp)
        Text(value, fontWeight = FontWeight.Bold, fontSize = 16.sp)
        Divider(modifier = Modifier.padding(top = 8.dp))
    }
}
