package com.example.avc.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.avc.R
import com.example.avc.domain.model.Product

@Composable
fun HomeItem(
    product: Product
) {
    val circleBackground = colorResource(id = R.color.green)

    Card(
        modifier = Modifier.fillMaxSize(),
        border = BorderStroke(1.dp, colorResource(id = R.color.light_gray)),
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box {
                Image(
                    modifier = Modifier
                        .width(70.dp)
                        .height(70.dp),
                    painter = painterResource(id = product.image),
                    contentDescription = stringResource(
                        id = R.string.cocacola
                    )
                )

                Text(
                    modifier = Modifier
                        .drawBehind {
                            drawCircle(
                                color = circleBackground,
                                radius = this.size.maxDimension
                            )
                        }
                        .align(Alignment.BottomCenter),
                    text = "${product.price.toInt()}€",
                    fontSize = 12.sp
                )
            }

            Text(
                text = product.name,
                fontSize = 20.sp
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp, alignment = Alignment.CenterHorizontally)
            ) {
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.green)),
                    onClick = { }
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(product.stock.toString())
                        Spacer(modifier = Modifier.width(2.dp))
                        Image(
                            painter = painterResource(id = R.drawable.ic_stock),
                            contentDescription = stringResource(id = R.string.stock)
                        )
                    }
                }

                Button(
                    border = BorderStroke(width = 1.dp, color = colorResource(id = R.color.green)),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                    onClick = { }
                ) {
                    Image(
                        modifier = Modifier.width(20.dp).height(20.dp),
                        painter = painterResource(id = R.drawable.ic_edit),
                        contentDescription = stringResource(id = R.string.edit)
                    )
                }
            }
        }
    }
}
