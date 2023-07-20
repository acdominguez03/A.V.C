package com.example.avc.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.avc.R
import com.example.avc.database.entity.DeliveryStatus
import com.example.avc.domain.model.DeliveryItemModel
import com.example.avc.presentation.viewModel.DeliveryState
import com.example.avc.presentation.viewModel.DeliveryViewModel

@Composable
fun DeliveryItem(
    item: DeliveryItemModel,
    state: DeliveryState,
    uiEvents: (DeliveryViewModel.DeliveryEvent) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxSize(),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp, bottom = 5.dp)
            ) {
                Text(
                    modifier = Modifier.align(Alignment.CenterStart).padding(start = 20.dp),
                    text = item.product.name,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.ExtraBold
                )

                CustomDropDownMenu(
                    modifier = Modifier.align(Alignment.CenterEnd).padding(end = 20.dp),
                    values = arrayOf("1", "2", "3", "4", "5"),
                    item = item,
                    uiEvents = uiEvents
                )
            }

            LazyRow(
                modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                items(state.checkBoxOptions) {
                    CustomCheckbox(
                        status = it,
                        item = item,
                        uiEvents = uiEvents
                    )
                }
            }
        }
    }
}

@Composable
fun CustomDropDownMenu(
    item: DeliveryItemModel,
    modifier: Modifier,
    values: Array<String>,
    uiEvents: (DeliveryViewModel.DeliveryEvent) -> Unit
) {
    Row(
        modifier = modifier
            .clickable {
                uiEvents(
                    DeliveryViewModel.DeliveryEvent.OnQuantityDropDownTouched(item)
                )
            }
    ) {
        Text(
            text = "Cantidad: ${item.quantity}",
            fontSize = 20.sp
        )
        Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "arrow")

        DropdownMenu(expanded = item.expandDropDown, onDismissRequest = {
            uiEvents(
                DeliveryViewModel.DeliveryEvent.OnDismissQuantityDropDown(item)
            )
        }) {
            values.forEach {
                DropdownMenuItem(
                    onClick = {
                        uiEvents(
                            DeliveryViewModel.DeliveryEvent.OnQuantitySelected(quantity = it, item = item)
                        )
                    }
                ) {
                    Text(text = it)
                }
            }
        }
    }
}

@Composable
fun CustomCheckbox(
    status: DeliveryStatus,
    item: DeliveryItemModel,
    uiEvents: (DeliveryViewModel.DeliveryEvent) -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = item.checkBoxStatus == status,
            onCheckedChange = {
                uiEvents(
                    DeliveryViewModel.DeliveryEvent.OnCheckBoxSelected(status, item)
                )
            },
            colors = CheckboxDefaults.colors(
                checkmarkColor = colorResource(id = R.color.checkbox_mark_color),
                checkedColor = colorResource(id = R.color.light_gray)
            )
        )
        Text(
            modifier = Modifier.padding(end = 12.dp),
            text = status.name,
            fontSize = 15.sp
        )
    }
}
