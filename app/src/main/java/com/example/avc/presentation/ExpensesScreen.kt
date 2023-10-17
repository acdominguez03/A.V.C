package com.example.avc.presentation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.avc.R
import com.example.avc.composables.CustomExpensesDialog
import com.example.avc.composables.CustomTopBar
import com.example.avc.database.entity.UserEntity
import com.example.avc.domain.model.ExpensesItemModel
import com.example.avc.presentation.viewModel.ExpensesState
import com.example.avc.presentation.viewModel.ExpensesViewModel

@Composable
fun ExpensesScreen(
    viewModel: ExpensesViewModel,
    uiEvents: (ExpensesViewModel.ExpensesEvent) -> Unit
) {
    var boxSize by remember { mutableStateOf(Size.Zero) }
    val state = viewModel.uiState.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.background)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomTopBar(title = stringResource(id = R.string.expenses_title))

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp, vertical = 20.dp)
                .onGloballyPositioned { coordinates ->
                    boxSize = coordinates.size.toSize()
                },
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(state.usersWithExpenses) {
                ExpensesItem(
                    user = it,
                    items = viewModel.getUserExpenses(it.id),
                    boxSize = boxSize,
                    state = state,
                    uiEvents = uiEvents
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExpensesItem(
    user: UserEntity,
    items: MutableList<ExpensesItemModel>,
    boxSize: Size,
    state: ExpensesState,
    uiEvents: (ExpensesViewModel.ExpensesEvent) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var money: Double = if (items.isNotEmpty()) {
        var finalMoney = 0.0
        items.forEach {
            finalMoney += it.productPrice * it.quantity
        }
        finalMoney
    } else {
        0.0
    }

    Card(
        modifier = Modifier
            .padding(horizontal = 5.dp)
            .width(boxSize.width.dp),
        elevation = 10.dp
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(Color.White)
            ) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterStart),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = user.name,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold
                        )

                        Text(
                            modifier = Modifier,
                            text = "$money€",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                        IconButton(
                            onClick = {
                                expanded = !expanded
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.ArrowDropDown,
                                contentDescription = "arrow"
                            )
                        }
                    }
                }
            }

            if (expanded) {
                LazyColumn(
                    modifier = Modifier.heightIn(max = 200.dp)
                ) {
                    itemsIndexed(
                        items = items,
                        key = { _, item ->
                            item.hashCode()
                        }
                    ) { _, item ->
                        val currentItem by rememberUpdatedState(newValue = item)
                        val dismissState = rememberDismissState(
                            confirmStateChange = {
                                if (it == DismissValue.DismissedToStart) {
                                    items.remove(currentItem)
                                } else if (it == DismissValue.DismissedToEnd) {
                                    uiEvents(
                                        ExpensesViewModel.ExpensesEvent.ShowExpensesDialog
                                    )
                                }
                                true
                            }
                        )

                        SwipeToDismiss(
                            state = dismissState,
                            modifier = Modifier
                                .padding(vertical = 1.dp),
                            background = {
                                SwipeBackground(dismissState = dismissState)
                            },
                            dismissContent = {
                                ProductItem(
                                    expensesItem = item,
                                    state = state,
                                    uiEvents = uiEvents
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterialApi::class)
fun SwipeBackground(dismissState: DismissState) {
    val direction = dismissState.dismissDirection ?: return

    val color by animateColorAsState(
        when (dismissState.targetValue) {
            DismissValue.Default -> Color.LightGray
            DismissValue.DismissedToEnd -> Color.Green
            DismissValue.DismissedToStart -> Color.Red
        }
    )
    val alignment = when (direction) {
        DismissDirection.StartToEnd -> Alignment.CenterStart
        DismissDirection.EndToStart -> Alignment.CenterEnd
    }
    val icon = when (direction) {
        DismissDirection.StartToEnd -> Icons.Default.Edit
        DismissDirection.EndToStart -> Icons.Default.Delete
    }
    val scale by animateFloatAsState(
        if (dismissState.targetValue == DismissValue.Default) 0.75f else 1f
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
            .padding(8.dp),
        contentAlignment = alignment
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.align(Alignment.CenterEnd).scale(scale)
        )
    }
}

@Composable
fun ProductItem(
    state: ExpensesState,
    expensesItem: ExpensesItemModel,
    uiEvents: (ExpensesViewModel.ExpensesEvent) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .clip(RoundedCornerShape(10.dp))
            .padding(10.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(0.65f)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterStart),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "${expensesItem.quantity} x ",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = expensesItem.productName,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                modifier = Modifier
                    .align(Alignment.CenterEnd),
                text = "${expensesItem.quantity * expensesItem.productPrice}€",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }

    if (state.showExpensesDialog) {
        CustomExpensesDialog(
            onDismiss = {
                uiEvents(
                    ExpensesViewModel.ExpensesEvent.OnCancelExpensesDialog
                )
            },
            onConfirm = { quantity, item ->
                uiEvents(
                    ExpensesViewModel.ExpensesEvent.OnConfirmExpensesDialog(quantity = quantity, item = item)
                )
            },
            state = state,
            item = expensesItem,
            uiEvents = uiEvents
        )
    }
}
