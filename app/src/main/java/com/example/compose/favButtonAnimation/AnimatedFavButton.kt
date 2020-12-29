package com.example.compose.favButtonAnimation

import androidx.compose.animation.core.*
import androidx.compose.animation.core.AnimationConstants.Infinite
import androidx.compose.animation.transition
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.compose.ui.*

enum class ButtonState {
    IDLE, PRESSED
}

@Composable //2
fun AnimatedFavButton() {
    val buttonState = remember { mutableStateOf(ButtonState.IDLE) } //3

    //Transition Definition

    val transitionDefinition = transitionDefinition<ButtonState> {

        state(ButtonState.IDLE) {
            this[width] = 300.dp
            this[roundedCorners] = 6 // new code
            this[textColor] = purple500
            this[backgroundColor] = Color.White
            this[textOpacity] = 1f
            this[iconOpacity] = 0f
            this[pressedHeartSize] = 48.dp
            this[idleHeartIconSize] = 24.dp
        }

        state(ButtonState.PRESSED) {
            this[width] = 60.dp
            this[roundedCorners] = 50 // new code
            this[textColor] = Color.White
            this[backgroundColor] = purple500
            this[textOpacity] = 0f
            this[iconOpacity] = 1f
            this[pressedHeartSize] = 48.dp
            this[idleHeartIconSize] = 24.dp
        }

        transition(ButtonState.IDLE to ButtonState.PRESSED) {
            width using tween(durationMillis = 1500)
            // begin new code
            roundedCorners using tween(
                durationMillis = 3000,
                easing = FastOutLinearInEasing
            )

            backgroundColor using tween(durationMillis = 3000)
            textColor using tween(durationMillis = 500)

            textOpacity using tween(durationMillis = 1500)
            iconOpacity using tween(durationMillis = 1500)

            pressedHeartSize using keyframes {
                durationMillis = 2200
                48.dp at 1700
                12.dp at 1900
            }
            // end new code
        }

        transition(ButtonState.PRESSED to ButtonState.IDLE) {
            width using tween(durationMillis = 1500)
            // begin new code
            roundedCorners using tween(
                durationMillis = 3000,
                easing = FastOutLinearInEasing
            )

            backgroundColor using tween(durationMillis = 3000)
            textColor using tween(durationMillis = 500)

            textOpacity using tween(durationMillis = 3000)
            iconOpacity using tween(durationMillis = 3000)

            idleHeartIconSize using repeatable( // 1
                animation = keyframes { //2
                    durationMillis = 2000
                    24.dp at 1400
                    12.dp at 1500
                    24.dp at 1600
                    12.dp at 1700
                },
                iterations = Infinite
            ) //3
        }
        // end new code
    }

    val toState = if (buttonState.value == ButtonState.IDLE) {
        ButtonState.PRESSED
    } else {
        ButtonState.IDLE
    }

    //5
    val state = transition(
        definition = transitionDefinition,
        initState = buttonState.value,
        toState = toState
    )

    FavButton(buttonState, state = state)
}