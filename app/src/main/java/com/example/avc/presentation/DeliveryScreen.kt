package com.example.avc.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.avc.R
import com.example.avc.composables.CustomButton
import com.example.avc.composables.CustomDropDownMenuTextField
import com.example.avc.composables.CustomTopBar
import com.example.avc.composables.DeliveryItem

@Composable
fun DeliveryScreen() {
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
            items(5) {
                DeliveryItem()
            }
        }

        CustomButton(text = stringResource(id = R.string.add)) {
            // TODO: Add new delivery
        }
    }
}

