package org.junia.friendbook.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import friendbook.composeapp.generated.resources.Res
import friendbook.composeapp.generated.resources.addbutton
import org.jetbrains.compose.resources.painterResource
import org.junia.friendbook.data.Hobby
import org.junia.friendbook.ui.viewmodels.HobbyViewModel

@Composable
fun HobbyListScreen(
    navController: NavHostController,
    hobbyViewmodel: HobbyViewModel,
    modifier: Modifier = Modifier,
) {
    LaunchedEffect(Unit) {
        hobbyViewmodel.getHobbies()
    }

    Scaffold(
        topBar = {
            HobbyListBar(
                hobbyViewmodel,
                Modifier.statusBarsPadding()
            )
        },
        bottomBar = {
            BottomNavBar(navController)
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(FriendbookScreen.Addhobby.name)
                }
            ) {
                Icon(
                    painter = painterResource(Res.drawable.addbutton),
                    contentDescription = "Agregar hobby"
                )
            }
        },
    ) { innerPadding ->
        Spacer(modifier = Modifier.height(20.dp))
        HobbyList(
            hobbyList = hobbyViewmodel.HobbyList,
            modifier = Modifier.padding(innerPadding),
            navController = navController,
            hobbyViewmodel = hobbyViewmodel
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HobbyListBar(
    hobbyViewmodel: HobbyViewModel,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        TopAppBar(
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Hobbies",
                        color = MaterialTheme.colorScheme.tertiary,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.width(25.dp))

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
fun HobbyList(
    hobbyList: List<Hobby>,
    modifier: Modifier = Modifier,
    navController: NavHostController,
    hobbyViewmodel: HobbyViewModel
) {
    LazyColumn(modifier = modifier) {
        items(hobbyList) { hobby ->
            HobbyCard(
                hobby = hobby,
                modifier = Modifier
                    .padding(0.dp)
                    .clickable {
                        hobbyViewmodel.setDetail(hobby.name)
                    }
            )
        }
    }

    val currentDetail by hobbyViewmodel::currentDetail
    if (currentDetail.id.isNotEmpty()) {
        LaunchedEffect(currentDetail) {
            navController.navigate(FriendbookScreen.Hobbydetail.name)
        }
    }
}

@Composable
fun HobbyCard(
    hobby: Hobby,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = hobby.name,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(8.dp))
        Divider(color = MaterialTheme.colorScheme.secondary, thickness = 1.dp)
    }
}
