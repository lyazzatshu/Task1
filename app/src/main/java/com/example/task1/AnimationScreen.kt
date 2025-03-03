package com.example.task1

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.*
import androidx.navigation.NavController

@Composable
fun AnimationScreen(navController: NavController) {
    var isRotated by remember { mutableStateOf(false) }
    var isVisible by remember { mutableStateOf(true) }
    var isMoving by remember { mutableStateOf(false) }

    val rotation by animateFloatAsState(targetValue = if (isRotated) 360f else 0f, label = "")

    val scale by animateFloatAsState(targetValue = if (isRotated) 1.2f else 1f, label = "")

    val buttonColor by animateColorAsState(
        targetValue = if (isRotated) Color.Red else MaterialTheme.colorScheme.primary,
        label = ""
    )

    val buttonOffset by animateDpAsState(
        targetValue = if (isMoving) 50.dp else 0.dp,
        animationSpec = tween(durationMillis = 500),
        label = ""
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Button(
            onClick = {
                isRotated = !isRotated
                isVisible = !isVisible
                isMoving = !isMoving
            },
            colors = ButtonDefaults.buttonColors(containerColor = buttonColor),
            modifier = Modifier
                .scale(scale)
                .offset(y = buttonOffset)
                .height(50.dp)
        ) {
            Text("Tap me!", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier.height(50.dp))

        AnimatedVisibility(
            visible = isVisible,
            enter = fadeIn(animationSpec = tween(500)) + expandVertically(),
            exit = fadeOut(animationSpec = tween(500)) + shrinkVertically()
        ) {
            Text("Hi, how are u?", fontSize = 22.sp, color = MaterialTheme.colorScheme.secondary)
        }

        Spacer(modifier = Modifier.height(100.dp))

        Button(
            onClick = { navController.navigate("lottie") },
            modifier = Modifier.height(50.dp)
        ) {
            Text("Go to Lottie Animation", fontSize = 16.sp)
        }
    }
}
