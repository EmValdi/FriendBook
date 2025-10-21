package org.junia.friendbook.ui.views


import androidx.compose.foundation.layout.padding

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.junia.friendbook.ui.viewmodels.FriendslistViewModel
import org.junia.friendbook.ui.viewmodels.HobbyViewModel
import org.junia.friendbook.uiAboutAccount.screens.about.AboutScreen
import org.junia.friendbook.uiAboutAccount.screens.account.AccountEditScreen
import org.junia.friendbook.uiAboutAccount.screens.account.AccountScreen

enum class FriendbookScreen(){
    Start,
    Login,
    Signup,
    Friendlist,
    About,
    Account,
    Frienddetail,
    Addfriend,
    Editfriend,
    Hobbylist,
    Addhobby,
    Hobbydetail,
    Edithobby,
    EditAccount
}

@Composable
fun FriendbookApp(
    navController: NavHostController = rememberNavController()
) {
    val friendsViewModel: FriendslistViewModel = viewModel()
    val hobbyViewModel: HobbyViewModel = viewModel()
    NavHost(navController = navController,
        startDestination = FriendbookScreen.Start.name,
        modifier = Modifier.padding()){
        composable(route = FriendbookScreen.Start.name){
            WelcomeScreen(navController)
        }
        composable(route = FriendbookScreen.Login.name){
            LoginScreen(navController)
        }
        composable(route = FriendbookScreen.Signup.name){
            SignupScreen(navController)
        }
        composable(route = FriendbookScreen.Friendlist.name){
            FriendlistScreen(navController, Modifier, friendsViewModel)
        }
        composable(route = FriendbookScreen.About.name){
            AboutScreen(navController)
        }
        composable(route = FriendbookScreen.Account.name){
            AccountScreen(navController)
        }
        composable(route = FriendbookScreen.Frienddetail.name){
            FriendDetailScreen(navController, friendsViewModel)
        }
        composable(route = FriendbookScreen.Addfriend.name){
            AddFriendScreen(navController)
        }
        composable(route = FriendbookScreen.Editfriend.name){
            EditFriendScreen(navController, friendsViewModel)
        }
        composable(route = FriendbookScreen.Hobbylist.name){
            HobbyListScreen(navController, hobbyViewModel)
        }
        composable(route = FriendbookScreen.Addhobby.name){
            AddHobbyScreen(navController)
        }
        composable(route = FriendbookScreen.Hobbydetail.name){
            HobbyDetailScreen(navController, hobbyViewModel)
        }
        composable(route = FriendbookScreen.Edithobby.name){
            HobbyEditScreen(navController, hobbyViewModel)
        }
        composable(route = FriendbookScreen.EditAccount.name){
            AccountEditScreen(navController)
        }
    }
}

