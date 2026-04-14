package com.example.cowboyz.firebase

import com.example.cowboyz.data.model.Address
import com.example.cowboyz.data.model.Order
import com.example.cowboyz.data.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

object FirebaseRepository {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    fun registerUser(
        name: String,
        email: String,
        phone: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email.trim(), password.trim())
            .addOnSuccessListener {
                val uid = auth.currentUser?.uid ?: ""

                if (uid.isEmpty()) {
                    onFailure("User ID not found")
                    return@addOnSuccessListener
                }

                val user = User(
                    uid = uid,
                    name = name.trim(),
                    email = email.trim(),
                    phone = phone.trim()
                )

                db.collection("users")
                    .document(uid)
                    .set(user)
                    .addOnSuccessListener {
                        onSuccess()
                    }
                    .addOnFailureListener { e ->
                        onFailure(e.message ?: "Failed to save user data")
                    }
            }
            .addOnFailureListener { e ->
                onFailure(e.message ?: "Registration failed")
            }
    }

    fun loginUser(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        auth.signInWithEmailAndPassword(email.trim(), password.trim())
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { e ->
                onFailure(e.message ?: "Login failed")
            }
    }

    fun saveAddress(
        address: Address,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val id = db.collection("addresses").document().id
        val newAddress = address.copy(id = id)

        db.collection("addresses")
            .document(id)
            .set(newAddress)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { e ->
                onFailure(e.message ?: "Failed to save address")
            }
    }

    fun placeOrder(
        order: Order,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val id = db.collection("orders").document().id
        val newOrder = order.copy(id = id)

        db.collection("orders")
            .document(id)
            .set(newOrder)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { e ->
                onFailure(e.message ?: "Failed to place order")
            }
    }

    fun getCurrentUserId(): String {
        return auth.currentUser?.uid ?: ""
    }
}