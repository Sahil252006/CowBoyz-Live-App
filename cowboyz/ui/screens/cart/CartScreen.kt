package com.example.cowboyz.ui.screens.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cowboyz.data.sample.CartManager
import com.example.cowboyz.ui.components.PrimaryButton // ✅ IMPORTANT IMPORT

@Composable
fun CartScreen(navController: NavController) {
    val cartItems = CartManager.cartItems
    val total = CartManager.getCartTotal()

    if (cartItems.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Your Cart is Empty",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(12.dp))

            // ✅ REPLACED BUTTON
            PrimaryButton(
                text = "Continue Shopping",
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    navController.navigate("home")
                }
            )
        }
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "My Cart",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(cartItems) { item ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = item.product.name,
                            style = MaterialTheme.typography.titleMedium
                        )
                        Text(text = "Size: ${item.selectedSize}")
                        Text(text = "Price: ₹${item.product.price.toInt()}")

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            OutlinedButton(
                                onClick = {
                                    CartManager.decreaseQuantity(
                                        item.product.id,
                                        item.selectedSize
                                    )
                                }
                            ) {
                                Text("-")
                            }

                            Text(
                                text = item.quantity.toString(),
                                modifier = Modifier.padding(top = 12.dp)
                            )

                            OutlinedButton(
                                onClick = {
                                    CartManager.increaseQuantity(
                                        item.product.id,
                                        item.selectedSize
                                    )
                                }
                            ) {
                                Text("+")
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = "Subtotal: ₹${(item.product.price * item.quantity).toInt()}",
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }

        Text(
            text = "Total: ₹${total.toInt()}",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(12.dp))

        // ✅ REPLACED BUTTON
        PrimaryButton(
            text = "Checkout",
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                navController.navigate("checkout")
            }
        )
    }
}