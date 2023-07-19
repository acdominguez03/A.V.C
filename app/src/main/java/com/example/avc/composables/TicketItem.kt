package com.example.avc.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.avc.database.entity.ProductPerTicketEntity
import com.example.avc.database.entity.TicketEntity
import com.example.avc.presentation.utils.DateUtil
import com.example.avc.presentation.viewModel.TicketState
import com.example.avc.presentation.viewModel.TicketViewModel

@Composable
fun TicketItem(
    ticket: TicketEntity,
    viewModel: TicketViewModel
) {
    Card(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .border(
                width = 2.dp,
                color = Color.Black,
                shape = RoundedCornerShape(20.dp)
            ),
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier.padding(30.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Ticket ${DateUtil.convertLongToTime(ticket.date)}",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${ticket.price}€",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 100.dp, max = 200.dp)
                    .padding(horizontal = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(viewModel.getProductsForOneTicket(ticketId = ticket.id)) {
                    ProductItemTicket(
                        viewModel = viewModel,
                        product = it
                    )
                }
            }
        }
    }
}

@Composable
fun ProductItemTicket(
    viewModel: TicketViewModel,
    product: ProductPerTicketEntity
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "${viewModel.getNameOfTheProduct(product.productId)} -> ",
            fontSize = 20.sp
        )
        Text(
            text = "${product.amount} unidades",
            fontSize = 20.sp
        )
    }
}
