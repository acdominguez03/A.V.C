package com.example.avc.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.avc.R
import com.example.avc.composables.CustomTopBar
import com.example.avc.composables.TicketItem
import com.example.avc.database.entity.ProductEntity
import com.example.avc.domain.model.TicketItem
import com.example.avc.presentation.viewModel.TicketViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun TicketScreen(
    viewModel: TicketViewModel = koinViewModel(),
    modifier: Modifier
) {
    val state = viewModel.uiState.collectAsState().value

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.background)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomTopBar(title = "Tickets")

        LazyColumn(
            modifier = Modifier
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(state.tickets) {
                TicketItem(
                    ticket = it,
                    viewModel = viewModel
                )
            }
        }
    }
}
