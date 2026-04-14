package com.example.cowboyz.ui.screens.account

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

@Composable
fun AccountScreen(navController: NavController) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "My Account",
            style = MaterialTheme.typography.headlineMedium
        )

        Card {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Brand: CowBoyz Official Store")
                Text("Style: Premium Western Fashion")
                Text("Support: WhatsApp Available")
            }
        }

        Button(onClick = { navController.navigate("address") }) {
            Text("Manage Address")
        }

        Button(onClick = { navController.navigate("wishlist") }) {
            Text("My Wishlist")
        }

        Button(onClick = { navController.navigate("orders") }) {
            Text("My Orders")
        }

        Button(onClick = {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://wa.me/919999999999")
            )
            context.startActivity(intent)
        }) {
            Text("WhatsApp Support")
        }

        Button(onClick = {
            FirebaseAuth.getInstance().signOut()
            navController.navigate("login") {
                popUpTo(0)
            }
        }) {
            Text("Logout")
        }
    }
}