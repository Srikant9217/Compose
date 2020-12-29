package com.example.compose.favButtonAnimation

import androidx.compose.animation.core.TransitionState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonConstants
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.compose.ui.backgroundColor
import com.example.compose.ui.purple500
import com.example.compose.ui.roundedCorners
import com.example.compose.ui.width

@Composable
fun FavButton(buttonState: MutableState<ButtonState>, state: TransitionState) {
    Button(
        border = BorderStroke(1.dp, purple500),
        colors = ButtonConstants.defaultButtonColors(backgroundColor = state[backgroundColor]),
        shape = RoundedCornerShape(state[roundedCorners]),
        modifier = Modifier.size(state[width], 60.dp),
        onClick = {
            buttonState.value = if (buttonState.value == ButtonState.IDLE) {
                ButtonState.PRESSED
            } else {
                ButtonState.IDLE
            }
        }
    ) {
        ButtonContent(buttonState, state)
    }
}

