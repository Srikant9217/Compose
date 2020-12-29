package com.example.compose.favButtonAnimation

import androidx.compose.animation.core.TransitionState
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import com.example.compose.ui.*

@Composable
fun ButtonContent(
    buttonState: MutableState<ButtonState>,
    state: TransitionState
) {
    if (buttonState.value == ButtonState.PRESSED) { //1
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(
                Modifier.width(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    tint = state[textColor],
                    imageVector = Icons.Default.FavoriteBorder,
                    modifier = Modifier.size(state[idleHeartIconSize])
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                "ADD TO FAVORITES!",
                softWrap = false,
                modifier = Modifier.alpha(state[textOpacity]), //2
                color = state[textColor]
            )
        }
    } else {
        Icon( //3
            tint = state[textColor],
            imageVector = Icons.Default.Favorite,
            modifier = Modifier.size(state[pressedHeartSize]).alpha(state[iconOpacity]) //4
        )
    }
}