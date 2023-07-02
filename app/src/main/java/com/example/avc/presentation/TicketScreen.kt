package com.example.avc.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.avc.R
import com.example.avc.composables.CustomTopBar
import com.example.avc.composables.TicketItem

@Composable
fun TicketScreen() {
    Column(
        modifier = Modifier
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
            items(4) {
                TicketItem()
            }
        }
    }
}
