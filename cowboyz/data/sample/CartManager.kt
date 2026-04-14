package com.example.cowboyz.data.sample

import androidx.compose.runtime.mutableStateListOf
import com.example.cowboyz.data.model.CartItem
import com.example.cowboyz.data.model.Product

object CartManager {

    val cartItems = mutableStateListOf<CartItem>()

    fun addToCart(product: Product, size: String = "M") {
        val existingItem = cartItems.find {
            it.product.id == product.id && it.selectedSize == size
        }

        if (existingItem != null) {
            existingItem.quantity += 1
            refreshCart()
        } else {
            cartItems.add(CartItem(product = product, quantity = 1, selectedSize = size))
        }
    }

    fun increaseQuantity(productId: String, size: String) {
        val item = cartItems.find {
            it.product.id == productId && it.selectedSize == size
        }
        item?.let {
            it.quantity += 1
            refreshCart()
        }
    }

    fun decreaseQuantity(productId: String, size: String) {
        val item = cartItems.find {
            it.product.id == productId && it.selectedSize == size
        }
        item?.let {
            if (it.quantity > 1) {
                it.quantity -= 1
                refreshCart()
            } else {
                cartItems.remove(it)
            }
        }
    }

    fun removeItem(productId: String, size: String) {
        cartItems.removeAll { it.product.id == productId && it.selectedSize == size }
    }

    fun getCartTotal(): Double {
        return cartItems.sumOf { it.product.price * it.quantity }
    }

    fun getCartCount(): Int {
        return cartItems.sumOf { it.quantity }
    }

    private fun refreshCart() {
        val copy = cartItems.toList()
        cartItems.clear()
        cartItems.addAll(copy)
    }
}