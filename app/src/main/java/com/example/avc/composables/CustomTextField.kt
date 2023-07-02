package com.example.avc.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.avc.R

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    leadingIcon: @Composable () -> Unit,
    label: String
) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = { newText ->
            onValueChange(newText)
        },
        label = {
            Text(text = label, color = Color.Black)
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = colorResource(id = R.color.light_gray),
            cursorColor = Color.Black,
            focusedIndicatorColor = Color.Black
        ),
        shape = RoundedCornerShape(5.dp),
        leadingIcon = leadingIcon,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@Composable
fun CustomButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(id = R.color.green)
        ),
        onClick = { onClick() }
    ) {
        Text(
            text = text,
            modifier = Modifier
                .padding(horizontal = 40.dp, vertical = 5.dp),
            fontSize = 20.sp
        )
    }
}
