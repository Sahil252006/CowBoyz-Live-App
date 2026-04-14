package com.example.cowboyz.ui.screens.checkout

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cowboyz.data.model.Order
import com.example.cowboyz.data.sample.CartManager
import com.example.cowboyz.firebase.FirebaseRepository
import com.example.cowboyz.ui.components.PrimaryButton
import com.razorpay.Checkout
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun CheckoutScreen(navController: NavController) {
    val context = LocalContext.current
    val paymentMethod = remember { mutableStateOf("Cash on Delivery") }

    fun startPayment() {
        val checkout = Checkout()
        checkout.setKeyID("rzp_test_Sd3xwe8dOxtbJo")

        try {
            val options = JSONObject()
            options.put("name", "CowBoyz")
            options.put("description", "Purchase from CowBoyz")
            options.put("theme.color", "#3399cc")
            options.put("currency", "INR")
            options.put("amount", (CartManager.getCartTotal() * 100).toInt()) // amount in paise

            val retryObj = JSONObject()
            retryObj.put("enabled", true)
            retryObj.put("max_count", 4)
            options.put("retry", retryObj)

            val prefill = JSONObject()
            prefill.put("email", "customer@example.com")
            prefill.put("contact", "9876543210")
            options.put("prefill", prefill)

            checkout.open(context as Activity, options)
        } catch (e: Exception) {
            Toast.makeText(context, "Error in payment: " + e.message, Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }
    }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Checkout",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text("Select Payment Method")

        listOf("Cash on Delivery", "Online Payment").forEach { method ->
            Row {
                RadioButton(
                    selected = paymentMethod.value == method,
                    onClick = { paymentMethod.value = method }
                )
                Text(
                    text = method,
                    modifier = Modifier.padding(top = 12.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        PrimaryButton(
            text = if (paymentMethod.value == "Online Payment") "Pay Now" else "Place Order",
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                if (paymentMethod.value == "Online Payment") {
                    startPayment()
                } else {
                    val order = Order(
                        userId = FirebaseRepository.getCurrentUserId(),
                        items = CartManager.cartItems.toList(),
                        totalAmount = CartManager.getCartTotal(),
                        paymentMethod = paymentMethod.value,
                        date = SimpleDateFormat(
                            "dd-MM-yyyy HH:mm",
                            Locale.getDefault()
                        ).format(Date())
                    )

                    FirebaseRepository.placeOrder(
                        order = order,
                        onSuccess = {
                            CartManager.cartItems.clear()
                            Toast.makeText(context, "Order placed successfully", Toast.LENGTH_SHORT).show()
                            navController.navigate("orders")
                        },
                        onFailure = { error ->
                            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
                        }
                    )
                }
            }
        )
    }
}