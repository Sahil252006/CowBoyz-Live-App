package com.example.cowboyz.ui.screens.orders

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OrdersScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "My Orders",
            style = MaterialTheme.typography.headlineMedium
        )

        Card {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Order #CBZ1001", style = MaterialTheme.typography.titleMedium)
                Text("CowBoyz Denim Jacket x 1")
                Text("Status: Packed")
                Text("Expected Delivery: 18 Apr 2026")
            }
        }

        Card {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Order #CBZ1002", style = MaterialTheme.typography.titleMedium)
                Text("Classic Cowboy Hat x 2")
                Text("Status: Out for Delivery")
                Text("Expected Delivery: Today")
            }
        }
    }
}