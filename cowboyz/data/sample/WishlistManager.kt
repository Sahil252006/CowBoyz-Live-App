package com.example.cowboyz.data.sample

import androidx.compose.runtime.mutableStateListOf
import com.example.cowboyz.data.model.Product
import com.example.cowboyz.data.model.WishlistItem

object WishlistManager {

    val wishlistItems = mutableStateListOf<WishlistItem>()

    fun addToWishlist(product: Product) {
        val alreadyExists = wishlistItems.any { it.product.id == product.id }
        if (!alreadyExists) {
            wishlistItems.add(WishlistItem(product))
        }
    }

    fun removeFromWishlist(productId: String) {
        wishlistItems.removeAll { it.product.id == productId }
    }

    fun isInWishlist(productId: String): Boolean {
        return wishlistItems.any { it.product.id == productId }
    }
}