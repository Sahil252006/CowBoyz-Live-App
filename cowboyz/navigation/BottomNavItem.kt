package com.example.cowboyz.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    data object Home : BottomNavItem(Routes.HOME, "Home", Icons.Default.Home)
    data object Wishlist : BottomNavItem(Routes.WISHLIST, "Wishlist", Icons.Default.Favorite)
    data object Cart : BottomNavItem(Routes.CART, "Cart", Icons.Default.ShoppingCart)
    data object Orders : BottomNavItem(Routes.ORDERS, "Orders", Icons.Default.ShoppingBag)
    data object Account : BottomNavItem(Routes.ACCOUNT, "Account", Icons.Default.Person)
}