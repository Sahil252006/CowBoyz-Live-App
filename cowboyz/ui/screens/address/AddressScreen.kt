package com.example.cowboyz.ui.screens.address

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cowboyz.data.model.Address
import com.example.cowboyz.firebase.FirebaseRepository
import com.example.cowboyz.ui.components.PrimaryButton // ✅ IMPORTANT IMPORT

@Composable
fun AddressScreen(navController: NavController) {
    val context = LocalContext.current

    val name = remember { mutableStateOf("") }
    val phone = remember { mutableStateOf("") }
    val city = remember { mutableStateOf("") }
    val state = remember { mutableStateOf("") }
    val pin = remember { mutableStateOf("") }
    val address = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Add Address",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name.value,
            onValueChange = { name.value = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Full Name") }
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = phone.value,
            onValueChange = { phone.value = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Phone") }
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = city.value,
            onValueChange = { city.value = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("City") }
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = state.value,
            onValueChange = { state.value = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("State") }
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = pin.value,
            onValueChange = { pin.value = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Pin Code") }
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = address.value,
            onValueChange = { address.value = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Address") }
        )

        Spacer(modifier = Modifier.height(20.dp))

        // ✅ REPLACED BUTTON
        PrimaryButton(
            text = "Save Address",
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                val addr = Address(
                    uid = FirebaseRepository.getCurrentUserId(),
                    fullName = name.value.trim(),
                    phone = phone.value.trim(),
                    city = city.value.trim(),
                    state = state.value.trim(),
                    pinCode = pin.value.trim(),
                    addressLine = address.value.trim()
                )

                FirebaseRepository.saveAddress(
                    address = addr,
                    onSuccess = {
                        Toast.makeText(context, "Address saved", Toast.LENGTH_SHORT).show()
                        navController.navigate("checkout")
                    },
                    onFailure = { error ->
                        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
                    }
                )
            }
        )
    }
}