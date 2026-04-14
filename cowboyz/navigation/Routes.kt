package com.example.cowboyz.navigation

object Routes {
    const val SPLASH = "splash"
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val HOME = "home"
    const val CATEGORIES = "categories"
    const val CART = "cart"
    const val ORDERS = "orders"
    const val ACCOUNT = "account"
    const val ADDRESS = "address"
    const val CHECKOUT = "checkout"
    const val WISHLIST = "wishlist"
    const val PRODUCT_DETAILS = "product_details/{productId}"

    const val WOMEN_CATEGORIES = "women_categories"
    const val MEN_CATEGORIES = "men_categories"

    const val CATEGORY_PRODUCTS = "category_products/{mainCategory}/{subCategory}"

    fun productDetails(productId: String): String {
        return "product_details/$productId"
    }

    fun categoryProducts(mainCategory: String, subCategory: String): String {
        return "category_products/$mainCategory/$subCategory"
    }
}