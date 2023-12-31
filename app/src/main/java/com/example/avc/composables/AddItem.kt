package com.example.avc.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.avc.R
import com.example.avc.domain.model.TicketItem
import com.example.avc.presentation.viewModel.AddTicketsViewModel

@Composable
fun AddItem(
    product: TicketItem,
    uiEvent: (AddTicketsViewModel.AddTicketsEvent) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxSize(),
        border = BorderStroke(1.dp, colorResource(id = R.color.light_gray)),
        shape = RoundedCornerShape(10.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp, alignment = Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    modifier = Modifier
                        .width(70.dp)
                        .height(70.dp)
                        .padding(start = 10.dp)
                        .align(Alignment.CenterStart),
                    painter = painterResource(id = product.image.mapToMyImageResource()),
                    contentDescription = stringResource(
                        id = R.string.cocacola
                    )
                )

                Text(
                    modifier = Modifier.padding(start = 80.dp).align(Alignment.CenterStart),
                    text = product.productName,
                    fontSize = 20.sp
                )

                Row(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    horizontalArrangement = Arrangement.spacedBy(
                        10.dp,
                        alignment = Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier
                            .clickable {
                                uiEvent(
                                    AddTicketsViewModel.AddTicketsEvent.OnRemoveQuantity(product = product)
                                )
                            }
                            .size(50.dp),
                        painter = painterResource(id = R.drawable.ic_substract),
                        contentDescription = stringResource(
                            id = R.string.substract
                        )
                    )

                    Text(
                        modifier = Modifier.clickable {
                            uiEvent(
                                AddTicketsViewModel.AddTicketsEvent.ShowDialog(product)
                            )
                        },
                        text = product.amount.toString(),
                        fontSize = 20.sp
                    )

                    Image(
                        modifier = Modifier
                            .clickable {
                                uiEvent(
                                    AddTicketsViewModel.AddTicketsEvent.OnAddQuantity(product = product)
                                )
                            }
                            .size(50.dp),
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = stringResource(
                            id = R.string.substract
                        )
                    )
                }
            }
        }
    }
}
