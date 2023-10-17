package com.example.avc.composables.custom_tab_bar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.avc.presentation.*
import com.example.avc.presentation.viewModel.AddTicketsViewModel
import com.example.avc.presentation.viewModel.DeliveryViewModel
import com.example.avc.presentation.viewModel.ExpensesViewModel
import org.koin.androidx.compose.koinViewModel

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
            val viewModel: AddTicketsViewModel = koinViewModel()
            AddTicketsScreen(viewModel = viewModel, uiEvents = { viewModel.handleEvent(it) })
        }

        composable(route = BottomBarScreen.Delivery.route) {
            val viewModel: DeliveryViewModel = koinViewModel()
            DeliveryScreen(viewModel = viewModel, uiEvents = { viewModel.handleEvent(it) })
        }

        composable(route = BottomBarScreen.Expenses.route) {
            val viewModel: ExpensesViewModel = koinViewModel()
            ExpensesScreen(viewModel = viewModel, uiEvents = { viewModel.handleEvent(it) })
        }

        composable(route = BottomBarScreen.Tickets.route) {
            TicketScreen(modifier = modifier)
        }
    }
}
