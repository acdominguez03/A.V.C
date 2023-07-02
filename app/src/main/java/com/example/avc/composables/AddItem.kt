package com.example.avc.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.avc.R

@Preview(widthDp = 150, heightDp = 180)
@Composable
fun AddItem() {
    var quantity by remember { mutableStateOf(0) }
    var showDialog by remember { mutableStateOf(false) }

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
            Box {
                Image(
                    modifier = Modifier
                        .width(70.dp)
                        .height(70.dp),
                    painter = painterResource(id = R.drawable.ic_cocacola),
                    contentDescription = stringResource(
                        id = R.string.cocacola
                    )
                )
            }

            Text(
                text = "Coca-Cola",
                fontSize = 20.sp
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(
                    10.dp,
                    alignment = Alignment.CenterHorizontally
                ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .clickable { if (quantity > 0) quantity-- }
                        .size(50.dp),
                    painter = painterResource(id = R.drawable.ic_substract),
                    contentDescription = stringResource(
                        id = R.string.substract
                    )
                )

                Text(
                    modifier = Modifier.clickable { showDialog = true },
                    text = quantity.toString(),
                    fontSize = 20.sp
                )

                Image(
                    modifier = Modifier
                        .clickable { quantity++ }
                        .size(50.dp),
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = stringResource(
                        id = R.string.substract
                    )
                )
            }
        }
    }

    if (showDialog) {
        CustomDialog(
            onDismiss = { showDialog = false },
            onConfirm = {
                quantity = it.toInt()
                showDialog = false
            },
            product = "Coca-Cola"
        )
    }
}
