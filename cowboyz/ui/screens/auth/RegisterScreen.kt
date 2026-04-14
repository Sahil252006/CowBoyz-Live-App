package com.example.cowboyz.ui.screens.auth

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.cowboyz.firebase.FirebaseRepository
import com.example.cowboyz.ui.components.PrimaryButton // ✅ IMPORTANT IMPORT

@Composable
fun RegisterScreen(onRegisterSuccess: () -> Unit) {
    val context = LocalContext.current

    val name = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val phone = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Create Account",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = name.value,
            onValueChange = { name.value = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Full Name") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = email.value,
            onValueChange = { email.value = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Email") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = phone.value,
            onValueChange = { phone.value = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Mobile Number") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Password") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(20.dp))

        // ✅ REPLACED BUTTON
        PrimaryButton(
            text = "Register",
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                val cleanName = name.value.trim()
                val cleanEmail = email.value.trim()
                val cleanPhone = phone.value.trim()
                val cleanPassword = password.value.trim()

                when {
                    cleanName.isEmpty() -> {
                        Toast.makeText(context, "Please enter full name", Toast.LENGTH_SHORT).show()
                    }
                    cleanEmail.isEmpty() -> {
                        Toast.makeText(context, "Please enter email", Toast.LENGTH_SHORT).show()
                    }
                    cleanPhone.isEmpty() -> {
                        Toast.makeText(context, "Please enter mobile number", Toast.LENGTH_SHORT).show()
                    }
                    cleanPassword.isEmpty() -> {
                        Toast.makeText(context, "Please enter password", Toast.LENGTH_SHORT).show()
                    }
                    cleanPassword.length < 6 -> {
                        Toast.makeText(context, "Password must be at least 6 characters", Toast.LENGTH_LONG).show()
                    }
                    else -> {
                        FirebaseRepository.registerUser(
                            name = cleanName,
                            email = cleanEmail,
                            phone = cleanPhone,
                            password = cleanPassword,
                            onSuccess = {
                                Toast.makeText(context, "Registration successful", Toast.LENGTH_SHORT).show()
                                onRegisterSuccess()
                            },
                            onFailure = { error ->
                                Toast.makeText(context, error, Toast.LENGTH_LONG).show()
                            }
                        )
                    }
                }
            }
        )
    }
}