package com.example.avc.composables.custom_tab_bar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.avc.presentation.*

@Composable
fun BottomNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen(navController = navController)
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
