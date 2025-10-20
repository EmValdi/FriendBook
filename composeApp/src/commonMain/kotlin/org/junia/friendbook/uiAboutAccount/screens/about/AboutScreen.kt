package org.junia.friendbook.uiAboutAccount.screens.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.jetbrains.compose.resources.painterResource
import friendbook.composeapp.generated.resources.Res
import friendbook.composeapp.generated.resources.about_page
import org.junia.friendbook.ui.views.BottomNavBar

/**
 * About screen (single page)
 *
 * Structure:
 *  - Safe top inset to avoid status bar overlap (clock, notch)
 *  - Title ("About")
 *  - Image (fills width, tall enough but does not hide the title area)
 *
 * NOTE:
 *  - No dots / pager. Just one static page.
 *  - Any header outside this screen is NOT included here.
 */

@Composable
fun AboutScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Scaffold(
        bottomBar = {
            BottomNavBar(navController)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                // Respect Scaffold insets
                .padding(innerPadding)
                // Add safe area for status bar so the title never overlaps the clock/notch
                .windowInsetsPadding(WindowInsets.statusBars)
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // --- Title ---
            Text(
                text = "About",
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold)
            )

            Spacer(Modifier.height(16.dp))

            // --- Image (single) ---
            // Use ContentScale.Crop if you want edge-to-edge feel,
            // or ContentScale.Fit to avoid any cropping.
            Image(
                painter = painterResource(Res.drawable.about_page),
                contentDescription = "About image",
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(0.6f), // tune (0.55–0.7) to match your design height
                contentScale = ContentScale.Crop
            )

            // NOTE:
            // Header (outside this file) should be placed by the caller screen.
        }
    }
}