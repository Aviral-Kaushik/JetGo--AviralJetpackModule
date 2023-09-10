package com.aviral.jetpackbasics.navigrationDrawer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.aviral.jetpackbasics.ui.theme.JetpackBasicsTheme
import kotlinx.coroutines.launch

class NavigationDrawer : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackBasicsTheme {

                val scaffoldState = rememberScaffoldState()
                val scope = rememberCoroutineScope()

                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = {
                        AppBar(
                            onNavigationIconClick = {
                                scope.launch {
                                    scaffoldState.drawerState.open()
                                }
                            }
                        )
                    },
                    drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
                    drawerContent = {
                        DrawerHeader()

                        DrawerBody(
                            items = listOf(
                                NavDrawerMenuItem(
                                    id = "home",
                                    title = "Home",
                                    contentDescription = "Go to Home Screen",
                                    icon = Icons.Default.Home
                                ),
                                NavDrawerMenuItem(
                                    id = "settings",
                                    title = "Settings",
                                    contentDescription = "Go to Settings Screen",
                                    icon = Icons.Default.Settings
                                ),
                                NavDrawerMenuItem(
                                    id = "help",
                                    title = "Help",
                                    contentDescription = "Go to help Screen",
                                    icon = Icons.Default.Info
                                ),
                            ),
                            onItemClick = {
                                println(" Clicked on ${it.title}")
//                                when (it.id) {
//                                    "home" -> navigateToHomeScreen(),
//                                    "settings" -> navigateToSettingsScreen(),
//                                    "help" -> navigateToHelpScreen()
//                                }
                            }
                        )
                    }
                ) {

                    Text(text = "Hello", Modifier.padding(it))

                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ShowPreview() {
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            AppBar(
                onNavigationIconClick = {
                    scope.launch {
                        scaffoldState.drawerState.open()
                    }
                }
            )
        },
        drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
        drawerContent = {
            DrawerHeader()

            DrawerBody(
                items = listOf(
                    NavDrawerMenuItem(
                        id = "home",
                        title = "Home",
                        contentDescription = "Go to Home Screen",
                        icon = Icons.Default.Home
                    ),
                    NavDrawerMenuItem(
                        id = "settings",
                        title = "Settings",
                        contentDescription = "Go to Settings Screen",
                        icon = Icons.Default.Settings
                    ),
                    NavDrawerMenuItem(
                        id = "help",
                        title = "Help",
                        contentDescription = "Go to help Screen",
                        icon = Icons.Default.Info
                    ),
                ),
                onItemClick = {
                    println(" Clicked on ${it.title}")
//                                when (it.id) {
//                                    "home" -> navigateToHomeScreen(),
//                                    "settings" -> navigateToSettingsScreen(),
//                                    "help" -> navigateToHelpScreen()
//                                }
                }
            )
        }
    ) {

        Text(text = "Hello", Modifier.padding(it))

    }
}   