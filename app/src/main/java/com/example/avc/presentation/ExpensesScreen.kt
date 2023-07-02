package com.example.avc.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.avc.composables.CustomTopBar
import com.example.avc.domain.model.Product

@Composable
fun ExpensesScreen() {
    var boxSize by remember { mutableStateOf(Size.Zero) }

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
            items(4) {
                val items = remember {
                    mutableStateListOf<ProductSale>(
                        ProductSale(
                            product = Product(
                                id = 0,
                                name = "Coca-Cola",
                                price = 1.0,
                                image = 0,
                                stock = 10
                            ),
                            quantity = 4
                        ),
                        ProductSale(
                            product = Product(
                                id = 0,
                                name = "Coca-Cola Zero",
                                price = 1.0,
                                image = 0,
                                stock = 10
                            ),
                            quantity = 2
                        )
                    )
                }

                ExpensesItem(
                    name = "Pedro Panadero",
                    items = items,
                    boxSize = boxSize
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExpensesItem(
    name: String,
    items: MutableList<ProductSale>,
    boxSize: Size
) {
    var expanded by remember { mutableStateOf(false) }
    var money: Double = if (items.isNotEmpty()) {
        var finalMoney = 0.0
        items.forEach {
            finalMoney += it.product.price * it.quantity
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
                            text = name,
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
                            onClick = { expanded = !expanded }
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
                                }
                                true
                            }
                        )

                        SwipeToDismiss(
                            state = dismissState,
                            modifier = Modifier
                                .padding(vertical = 1.dp),
                            background = {
                                val color = when (dismissState.dismissDirection) {
                                    DismissDirection.StartToEnd -> Color.Transparent
                                    DismissDirection.EndToStart -> Color.Red
                                    null -> Color.Transparent
                                }

                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(color)
                                        .padding(8.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.align(Alignment.CenterEnd)
                                    )
                                }
                            },
                            directions = setOf(DismissDirection.EndToStart),
                            dismissContent = {
                                ProductItem(productSale = item)
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ProductItem(productSale: ProductSale) {
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
                    text = "${productSale.quantity} x ",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = productSale.product.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                modifier = Modifier
                    .align(Alignment.CenterEnd),
                text = "${productSale.quantity * productSale.product.price}€",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

data class ProductSale(
    val product: Product,
    val quantity: Int
)
