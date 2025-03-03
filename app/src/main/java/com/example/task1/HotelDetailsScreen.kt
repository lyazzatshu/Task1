package com.example.task1

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BookingDialog(onDismiss: () -> Unit) {
    var isVisible by remember { mutableStateOf(true) }

    AnimatedVisibility(visible = isVisible, enter = fadeIn(), exit = fadeOut()) {
        AlertDialog(
            onDismissRequest = { isVisible = false; onDismiss() },
            confirmButton = {
                Button(
                    onClick = { isVisible = false; onDismiss() },
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) { Text("Apply") }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = { isVisible = false; onDismiss() },
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = MaterialTheme.colorScheme.secondary)
                ) { Text("Cancel") }
            },
            title = { Text("Choose Your Filter", style = MaterialTheme.typography.titleLarge) },
            text = {
                Column(modifier = Modifier.padding(16.dp)) {
                    FilterSlider("Number of People", 1f, 10f)
                    FilterSlider("Number of Nights", 1f, 30f)
                    RatingSelector()
                }
            }
        )
    }
}

@Composable
fun FilterSlider(label: String, min: Float, max: Float) {
    var value by remember { mutableStateOf(min) }
    Text(label, style = MaterialTheme.typography.bodyMedium)
    Row(verticalAlignment = Alignment.CenterVertically) {
        Slider(
            value = value,
            onValueChange = { value = it },
            valueRange = min..max,
            modifier = Modifier.weight(1f)
        )
        Text(text = value.toInt().toString(), modifier = Modifier.padding(start = 8.dp))
    }
}

@Composable
fun RatingSelector() {
    var rating by remember { mutableStateOf(4) }
    Text("Hotel Class", style = MaterialTheme.typography.bodyMedium)
    Row {
        repeat(5) { index ->
            IconButton(onClick = { rating = index + 1 }) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "Star",
                    tint = if (index < rating) MaterialTheme.colorScheme.primary else Color.Gray
                )
            }
        }
    }
}

@Composable
fun HotelDetailsScreen(name: String, location: String, imageRes: List<Int>) {
    var showBookingDialog by remember { mutableStateOf(false) }

    val cardColors = listOf(
        Color(0xFF021024),
        Color(0xFF052659),
        Color(0xFF548383),
        Color(0xFF7DA0CA),
        Color(0xFFC1E8FF)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(cardColors[0], RoundedCornerShape(16.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(cardColors[1])
                .padding(8.dp)
        ) {
            items(imageRes) { image ->
                Image(
                    painter = painterResource(id = image),
                    contentDescription = name,
                    modifier = Modifier
                        .size(160.dp)
                        .padding(8.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(cardColors[2])
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = name, style = MaterialTheme.typography.headlineMedium, color = cardColors[3])
        Text(text = location, style = MaterialTheme.typography.bodyLarge, color = cardColors[4])
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Rating: ⭐⭐⭐⭐☆",
            modifier = Modifier
                .background(cardColors[2], RoundedCornerShape(12.dp))
                .padding(4.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = { showBookingDialog = true },
            colors = ButtonDefaults.buttonColors(containerColor = cardColors[1])
        ) {
            Text("Book Now", color = cardColors[4])
        }
        if (showBookingDialog) {
            BookingDialog(onDismiss = { showBookingDialog = false })
        }
    }
}
