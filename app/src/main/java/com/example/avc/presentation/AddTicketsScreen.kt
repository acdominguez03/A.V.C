package com.example.avc.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.avc.R
import com.example.avc.composables.AddItem
import com.example.avc.composables.CustomTextField
import com.example.avc.composables.CustomTopBar
import com.example.avc.presentation.viewModel.AddTicketsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddTicketsScreen(
    viewModel: AddTicketsViewModel = koinViewModel()
) {
    var priceText by remember {
        mutableStateOf("")
    }
    val products by viewModel.allProducts.collectAsState(
        initial = emptyList()
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.background))
    ) {
        CustomTopBar(
            title = stringResource(id = R.string.add_title)
        )

        Column(
            modifier = Modifier
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                modifier = Modifier.heightIn(0.dp, 590.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(products.size) { index ->
                    AddItem(
                        product = products[index]
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(20.dp, alignment = Alignment.CenterHorizontally)
            ) {
                CustomTextField(
                    value = priceText,
                    onValueChange = { newValue ->
                        priceText = newValue
                    },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_money_white),
                            contentDescription = stringResource(id = R.string.money),
                            tint = Color.Black
                        )
                    },
                    label = stringResource(id = R.string.price)
                )

                Image(
                    modifier = Modifier.size(50.dp),
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = stringResource(
                        id = R.string.add
                    )
                )
            }
        }
    }
}
