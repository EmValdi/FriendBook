package org.junia.friendbook.ui.views

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import friendbook.composeapp.generated.resources.Res
import friendbook.composeapp.generated.resources.friendsstart
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.junia.friendbook.ui.theme.FriendbookTheme

@Composable
@Preview
fun App() {
    FriendbookTheme{
        WelcomeScreen(modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center))
    }
}

@Composable
fun WelcomeScreen (modifier: Modifier = Modifier){
    Column(modifier = modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally)
        //verticalArrangement = Arrangement.Center)
    {
        Spacer(modifier = Modifier.height(100.dp))
        Image(
            painter = painterResource(Res.drawable.friendsstart),
            contentDescription = null,
            modifier = Modifier.size(300.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "Welcome!",
            style =
                    TextStyle(
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 40.sp
                    )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            "Ready to find a new way to remember all\nabout your friends?",
            style =
            TextStyle(
                color = Color.Gray,
                fontSize = 15.sp,
                textAlign = TextAlign.Center
            )
        )
        Spacer(modifier = Modifier.height(150.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {},
                modifier = Modifier
                    .width(150.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text("Log in")
            }
            Spacer(modifier = Modifier.width(32.dp))
            Button(
                onClick = {},
                modifier = Modifier
                    .width(150.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    contentColor = MaterialTheme.colorScheme.onBackground
                ),
                border = BorderStroke(1.dp, Color.Black)
            ) {
                Text("Sign up")
            }
        }
    }
}