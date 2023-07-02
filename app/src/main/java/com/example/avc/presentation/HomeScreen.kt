package com.example.avc.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.avc.R
import com.example.avc.composables.CustomTopBar
import com.example.avc.composables.HomeItem
import com.example.avc.composables.custom_tab_bar.BottomBarScreen
import com.example.avc.domain.model.Product

@Composable
fun HomeScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.background))
    ) {
        CustomTopBar(
            title = stringResource(id = R.string.home_title),
            withIcons = true,
            withProfits = true,
            modifier = Modifier
                .size(40.dp)
                .clickable { navController.navigate(BottomBarScreen.Tickets.route) }
        )

        Column(
            modifier = Modifier.fillMaxSize().padding(30.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(10) { item ->
                    HomeItem(
                        product = Product(
                            id = 0,
                            name = "Coca-Cola",
                            price = 1.0,
                            image = R.drawable.ic_cocacola,
                            stock = 10
                        )
                    )
                }
            }
        }
    }
}
