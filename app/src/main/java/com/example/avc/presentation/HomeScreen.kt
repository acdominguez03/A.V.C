package com.example.avc.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.avc.R
import com.example.avc.composables.CustomTopBar
import com.example.avc.composables.HomeItem
import com.example.avc.composables.custom_tab_bar.BottomBarScreen
import com.example.avc.database.entity.ProductEntity
import com.example.avc.presentation.viewModel.HomeViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = koinViewModel(),
    modifier: Modifier
) {
    val state = viewModel.uiState.collectAsState().value

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.background))
    ) {
        CustomTopBar(
            title = stringResource(id = R.string.home_title),
            withIcons = true,
            withProfits = true,
            modifier = Modifier
                .size(40.dp)
                .clickable {
                    navController.navigate(BottomBarScreen.Tickets.route)
                },
            profits = state.profits
        )

        Column(
            modifier = Modifier.fillMaxSize().padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                items(state.products) { product ->
                    HomeItem(
                        product = product
                    )
                }
            }
        }
    }
}
