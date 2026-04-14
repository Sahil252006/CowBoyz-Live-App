package com.example.cowboyz.ui.screens.wishlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cowboyz.data.sample.WishlistManager
import com.example.cowboyz.navigation.Routes
import com.example.cowboyz.ui.components.ProductCard

@Composable
fun WishlistScreen(navController: NavController) {
    val wishlistItems = WishlistManager.wishlistItems

    if (wishlistItems.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Your Wishlist is Empty",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(onClick = { navController.navigate(Routes.HOME) }) {
                Text("Explore Products")
            }
        }
        return
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentPadding = PaddingValues(bottom = 100.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = "My Wishlist",
                style = MaterialTheme.typography.headlineMedium
            )
        }

        items(wishlistItems) { item ->
            ProductCard(
                product = item.product,
                onClick = {
                    navController.navigate(Routes.productDetails(item.product.id))
                }
            )
        }
    }
}