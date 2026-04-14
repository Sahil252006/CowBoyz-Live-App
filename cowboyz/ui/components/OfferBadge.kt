package com.example.cowboyz.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun OfferBadge(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .background(
                color = Color(0xFFD32F2F), // 🔴 RED
                shape = RoundedCornerShape(50.dp)
            )
            .padding(horizontal = 14.dp, vertical = 6.dp),
        color = Color.White
    )
}