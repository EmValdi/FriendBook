package org.junia.friendbook.ui.views


import androidx.compose.foundation.layout.padding

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

enum class FriendbookScreen(){
    Start,
    Login,
    Signup,
    Friendlist
}

@Composable
fun FriendbookApp(
    navController: NavHostController = rememberNavController()
) {
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
    }
}

