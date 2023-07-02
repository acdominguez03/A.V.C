package com.example.avc.presentation

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.avc.composables.custom_tab_bar.BottomBar
import com.example.avc.composables.custom_tab_bar.BottomNavGraph

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomBar(
                navController = navController
            )
        }
    ) { paddingValues ->
        BottomNavGraph(navController = navController)
    }
}
