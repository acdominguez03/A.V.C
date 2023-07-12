package com.example.avc.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.avc.R
import com.example.avc.composables.CustomButton
import com.example.avc.composables.CustomDropDownMenuTextField
import com.example.avc.composables.CustomTopBar
import com.example.avc.composables.DeliveryItem
import com.example.avc.presentation.viewModel.DeliveryViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun DeliveryScreen(
    viewModel: DeliveryViewModel = koinViewModel()
) {
    val products by viewModel.allProducts.collectAsState(
        initial = emptyList()
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.background)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomTopBar(title = stringResource(id = R.string.delivery_title))

        CustomDropDownMenuTextField(items = listOf("Lucia", "Pedro", "Carlos", "Aaron"))

        LazyColumn(
            modifier = Modifier
                .padding(20.dp)
                .heightIn(min = 200.dp, max = 500.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(products.size) { index ->
                DeliveryItem(product = products[index])
            }
        }

        CustomButton(text = stringResource(id = R.string.add)) {
        }
    }
}
