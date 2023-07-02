package com.example.avc.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.avc.R

@Composable
fun DeliveryItem() {
    Card(
        modifier = Modifier.fillMaxSize(),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp, bottom = 5.dp),
                horizontalArrangement = Arrangement
                    .spacedBy(20.dp, alignment = Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Coca-Cola Zero",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.ExtraBold
                )

                CustomDropDownMenu(
                    values = arrayOf("1", "2", "3", "4", "5"),
                    text = "Cantidad: "
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                CustomCheckbox(text = stringResource(id = R.string.paid))
                CustomCheckbox(text = stringResource(id = R.string.no_paid))
                CustomCheckbox(text = stringResource(id = R.string.invitation))
            }
        }
    }
}

@Composable
fun CustomDropDownMenu(
    values: Array<String>,
    text: String?
) {
    var expanded by remember { mutableStateOf(false) }

    var currentValue by remember { mutableStateOf(values[0]) }

    Row(
        modifier = Modifier
            .clickable { expanded = !expanded }
    ) {
        Text(
            text = if (text != null) text + currentValue else currentValue,
            fontSize = 20.sp
        )
        Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "arrow")

        DropdownMenu(expanded = expanded, onDismissRequest = {
            expanded = false
        }) {
            values.forEach {
                DropdownMenuItem(
                    onClick = {
                        currentValue = it
                        expanded = false
                    }
                ) {
                    Text(text = it)
                }
            }
        }
    }
}


@Preview
@Composable
fun CustomCheckbox(
    text: String = "Pagado"
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        var checked by remember { mutableStateOf(false) }
        Checkbox(
            checked = checked,
            onCheckedChange = {
                checked = !checked
            },
            colors = CheckboxDefaults.colors(
                checkmarkColor = colorResource(id = R.color.checkbox_mark_color),
                checkedColor = colorResource(id = R.color.light_gray)

            )
        )
        Text(
            modifier = Modifier.padding(end = 12.dp),
            text = text,
            fontSize = 15.sp
        )
    }
}