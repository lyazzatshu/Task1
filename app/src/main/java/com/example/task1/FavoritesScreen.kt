package com.example.task1

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FavoritesScreen() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Favorite Hotels", style = MaterialTheme.typography.titleLarge)
        Card(modifier = Modifier.padding(top = 8.dp)) {
            Text(
                text = "Nusa Penida - Bali, Indonesia",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}
