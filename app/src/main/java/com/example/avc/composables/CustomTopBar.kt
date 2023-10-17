package com.example.avc.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.avc.R
import com.example.avc.composables.custom_tab_bar.BottomBarScreen

@Composable
fun CustomTopBar(
    title: String,
    withIcons: Boolean = false,
    withProfits: Boolean = false,
    modifier: Modifier = Modifier,
    profits: Double = 0.0
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.top_bar)),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (withIcons) {
            Image(
                modifier = modifier,
                painter = painterResource(id = R.drawable.ic_historical),
                contentDescription = stringResource(id = R.string.historical)
            )
        }

        Text(
            text = title,
            modifier = Modifier.padding(20.dp),
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 25.sp,
            textAlign = TextAlign.Center
        )

        if (withProfits) {
            Text(
                text = profits.toString(),
                color = if (profits < 0) {
                    colorResource(id = R.color.red)
                } else {
                    colorResource(id = R.color.green)
                },
                fontSize = 25.sp
            )
        }
    }
}