package com.example.avc.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.avc.R
import com.example.avc.database.entity.UserEntity
import com.example.avc.presentation.viewModel.DeliveryState
import com.example.avc.presentation.viewModel.DeliveryViewModel

@Composable
fun CustomDropDownMenuTextField(
    items: List<UserEntity>,
    uiEvents: (DeliveryViewModel.DeliveryEvent) -> Unit,
    state: DeliveryState
) {
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(top = 20.dp, start = 20.dp, end = 20.dp)
            .fillMaxWidth()
            .clickable(
                onClick = {
                    uiEvents(
                        DeliveryViewModel.DeliveryEvent.OnDismissDropDown("", user = null)
                    )
                }
            )
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .onGloballyPositioned { coordinates ->
                            textFieldSize = coordinates.size.toSize()
                        },
                    value = state.userText,
                    onValueChange = {
                        uiEvents(
                            DeliveryViewModel.DeliveryEvent.OnUserTextChanged(it)
                        )
                    },
                    label = { Text("Persona") },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = colorResource(id = R.color.light_gray),
                        focusedIndicatorColor = Color.Black,
                        unfocusedIndicatorColor = Color.Black,
                        cursorColor = Color.Black,
                        focusedLabelColor = Color.Black
                    ),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    singleLine = true,
                    trailingIcon = {
                        IconButton(onClick = { expanded = !expanded }) {
                            Icon(
                                imageVector = Icons.Rounded.ArrowDropDown,
                                contentDescription = "arrow"
                            )
                        }
                    }
                )
            }

            if (expanded) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                        .width(textFieldSize.width.dp),
                    elevation = 10.dp
                ) {
                    LazyColumn(
                        modifier = Modifier.heightIn(max = 200.dp)
                    ) {
                        if (state.userText.isNotEmpty()) {
                            items(
                                items.filter {
                                    it.name.lowercase()
                                        .contains(state.userText.lowercase())
                                }
                            ) {
                                PersonItem(title = it.name) { title ->
                                    uiEvents(
                                        DeliveryViewModel.DeliveryEvent.OnDismissDropDown(title, it)
                                    )
                                }
                            }
                        } else {
                            items(
                                items
                            ) {
                                PersonItem(title = it.name) { title ->
                                    uiEvents(
                                        DeliveryViewModel.DeliveryEvent.OnDismissDropDown(title, it)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PersonItem(
    title: String,
    onSelect: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onSelect(title)
            }
            .padding(10.dp)
    ) {
        Text(text = title, fontSize = 16.sp)
    }
}
