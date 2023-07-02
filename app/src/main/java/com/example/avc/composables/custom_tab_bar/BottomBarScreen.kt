package com.example.avc.composables.custom_tab_bar

import com.example.avc.R

sealed class BottomBarScreen(
    val route: String,
    val icon: Int
) {

    // Home
    object Home : BottomBarScreen(
        route = "home_screen",
        icon = R.drawable.ic_home
    )

    // Ticket
    object Ticket : BottomBarScreen(
        route = "add_tickets_screen",
        icon = R.drawable.ic_add_bar
    )

    // Delivery
    object Delivery : BottomBarScreen(
        route = "delivery_screen",
        icon = R.drawable.ic_delivery
    )

    // Expenses
    object Expenses : BottomBarScreen(
        route = "expenses_screen",
        icon = R.drawable.ic_money_white
    )

    // Tickets
    object Tickets : BottomBarScreen(
        route = "ticket_screen",
        icon = 0
    )
}
