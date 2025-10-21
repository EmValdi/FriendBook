package org.junia.friendbook.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import io.kamel.image.KamelImage
import org.jetbrains.compose.resources.painterResource
import org.junia.friendbook.data.interfaces.friend
import org.junia.friendbook.ui.Strings
import org.junia.friendbook.ui.viewmodels.FriendslistViewModel
import org.junia.friendbook.ui.viewmodels.LoginViewModel
import friendbook.composeapp.generated.resources.Res
import friendbook.composeapp.generated.resources.nopicture
import friendbook.composeapp.generated.resources.addbutton
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.launch
import org.junia.friendbook.data.Testdata

@Composable
fun FriendlistScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    friendslistViewmodel: FriendslistViewModel
){
    LaunchedEffect(Unit) {
        friendslistViewmodel.loadFriends()
    }

    Scaffold(
        topBar = {
            FriendlistBar(
                friendslistViewmodel,
                Modifier.statusBarsPadding()
            )
        },
        bottomBar = {
            BottomNavBar(navController)

        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(FriendbookScreen.Addfriend.name)
                }
            ) {
                Icon(painter = painterResource(Res.drawable.addbutton), contentDescription = "Agregar")
            }
        },
    ) { innerPadding ->
        Spacer(modifier = Modifier.height(20.dp))
        FriendList(friendList = friendslistViewmodel.userFriendsList,
            modifier = Modifier.padding(innerPadding),
            navController,
            friendslistViewmodel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FriendlistBar(
    friendslistViewmodel: FriendslistViewModel,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        TopAppBar(
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = Strings.friendlistTop,
                        color = MaterialTheme.colorScheme.tertiary,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.width(25.dp))
                    Text(
                        text = "${friendslistViewmodel.userFriendsList.size} friends",
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            },
            colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.background
            )
        )
        Divider(
            color = MaterialTheme.colorScheme.secondary,
            thickness = 1.dp
        )
    }
}

@Composable
fun FriendList(friendList: List<friend>,
               modifier: Modifier = Modifier,
               navController: NavHostController,
               friendslistViewmodel: FriendslistViewModel
){
    LazyColumn(modifier = modifier){
        items(friendList){
                friend -> FriendCard(
            friend = friend,
            modifier = Modifier.padding(0.dp)
                .clickable{
                    friendslistViewmodel.setDetail(friend.name)

                }
                )
        }
    }
    val currentDetail by friendslistViewmodel::currentDetail
    if (currentDetail.id != "") {
        LaunchedEffect(currentDetail) {
            navController.navigate(FriendbookScreen.Frienddetail.name)
        }
    }
}

@Composable
fun FriendCard(
    friend: friend,
    modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .height(68.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Avatar circular
        Box(
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.1f)),
            contentAlignment = Alignment.Center
        ) {
            if (friend.picture.isNotEmpty()) {
                val resource = asyncPainterResource(data = friend.picture)
                KamelImage(
                    resource,
                    contentDescription = "Friend photo",
                    contentScale = ContentScale.Crop,
                    onLoading = { CircularProgressIndicator() },
                    onFailure = {
                        Icon(
                            painter = painterResource(Res.drawable.nopicture),
                            contentDescription = "Loading Error",
                            modifier = Modifier.size(32.dp)
                        )
                    }
                )
            } else {
                Icon(
                    painter = painterResource(Res.drawable.nopicture),
                    contentDescription = "Default person",
                    modifier = Modifier.size(32.dp),
                    tint = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                )
            }
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                text = friend.name,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = friend.country,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
            )
            Spacer(modifier = Modifier.height(15.dp))
            Divider(color = MaterialTheme.colorScheme.secondary, thickness = 1.dp)
        }
    }
}