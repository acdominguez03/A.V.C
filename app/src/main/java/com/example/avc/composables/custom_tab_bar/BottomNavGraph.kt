package com.example.avc.composables.custom_tab_bar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.avc.presentation.*

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen(navController = navController, modifier = modifier)
        }

        composable(route = BottomBarScreen.Ticket.route) {
            AddTicketsScreen()
        }

        composable(route = BottomBarScreen.Delivery.route) {
            DeliveryScreen()
        }

        composable(route = BottomBarScreen.Expenses.route) {
            ExpensesScreen()
        }

        composable(route = BottomBarScreen.Tickets.route) {
            TicketScreen()
        }
    }
}
