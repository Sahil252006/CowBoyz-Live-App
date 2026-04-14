package com.example.cowboyz.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cowboyz.ui.components.BottomBar
import com.example.cowboyz.ui.screens.account.AccountScreen
import com.example.cowboyz.ui.screens.address.AddressScreen
import com.example.cowboyz.ui.screens.auth.LoginScreen
import com.example.cowboyz.ui.screens.auth.RegisterScreen
import com.example.cowboyz.ui.screens.cart.CartScreen
import com.example.cowboyz.ui.screens.categories.CategoryProductsScreen
import com.example.cowboyz.ui.screens.categories.CategoriesScreen
import com.example.cowboyz.ui.screens.categories.MenCategoryScreen
import com.example.cowboyz.ui.screens.categories.WomenCategoryScreen
import com.example.cowboyz.ui.screens.checkout.CheckoutScreen
import com.example.cowboyz.ui.screens.details.ProductDetailsScreen
import com.example.cowboyz.ui.screens.home.HomeScreen
import com.example.cowboyz.ui.screens.orders.OrdersScreen
import com.example.cowboyz.ui.screens.splash.SplashScreen
import com.example.cowboyz.ui.screens.wishlist.WishlistScreen

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.SPLASH
    ) {

        composable(Routes.SPLASH) {
            SplashScreen(
                onFinish = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.SPLASH) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.LOGIN) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                },
                onRegisterClick = {
                    navController.navigate(Routes.REGISTER)
                }
            )
        }

        composable(Routes.REGISTER) {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.navigate(Routes.HOME) {
                        popUpTo(Routes.REGISTER) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.HOME) {
            MainScaffoldScreen(
                currentRoute = Routes.HOME,
                navController = navController
            )
        }

        composable(Routes.WISHLIST) {
            MainScaffoldScreen(
                currentRoute = Routes.WISHLIST,
                navController = navController
            )
        }

        composable(Routes.CATEGORIES) {
            MainScaffoldScreen(
                currentRoute = Routes.CATEGORIES,
                navController = navController
            )
        }

        composable(Routes.CART) {
            MainScaffoldScreen(
                currentRoute = Routes.CART,
                navController = navController
            )
        }

        composable(Routes.ORDERS) {
            MainScaffoldScreen(
                currentRoute = Routes.ORDERS,
                navController = navController
            )
        }

        composable(Routes.ACCOUNT) {
            MainScaffoldScreen(
                currentRoute = Routes.ACCOUNT,
                navController = navController
            )
        }

        composable(Routes.ADDRESS) {
            AddressScreen(navController = navController)
        }

        composable(Routes.CHECKOUT) {
            CheckoutScreen(navController = navController)
        }

        composable(Routes.WOMEN_CATEGORIES) {
            WomenCategoryScreen(navController = navController)
        }

        composable(Routes.MEN_CATEGORIES) {
            MenCategoryScreen(navController = navController)
        }

        composable(
            route = Routes.CATEGORY_PRODUCTS,
            arguments = listOf(
                navArgument("mainCategory") {
                    type = NavType.StringType
                },
                navArgument("subCategory") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val mainCategory = backStackEntry.arguments?.getString("mainCategory") ?: ""
            val subCategory = backStackEntry.arguments?.getString("subCategory") ?: ""

            CategoryProductsScreen(
                navController = navController,
                mainCategory = mainCategory,
                subCategory = subCategory
            )
        }

        composable(
            route = Routes.PRODUCT_DETAILS,
            arguments = listOf(
                navArgument("productId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""

            ProductDetailsScreen(
                navController = navController,
                productId = productId
            )
        }
    }
}

@Composable
fun MainScaffoldScreen(
    currentRoute: String,
    navController: NavHostController
) {
    Scaffold(
        bottomBar = {
            BottomBar(navController = navController)
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(innerPadding)
        ) {
            when (currentRoute) {
                Routes.HOME -> HomeScreen(navController = navController)
                Routes.WISHLIST -> WishlistScreen(navController = navController)
                Routes.CATEGORIES -> CategoriesScreen(
                    navController = navController,
                    categoryName = "Women"
                )
                Routes.CART -> CartScreen(navController = navController)
                Routes.ORDERS -> OrdersScreen()
                Routes.ACCOUNT -> AccountScreen(navController = navController)
            }
        }
    }
}