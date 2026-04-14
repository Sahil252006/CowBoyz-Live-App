package com.example.cowboyz.ui.screens.categories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cowboyz.data.sample.SampleProducts
import com.example.cowboyz.navigation.Routes
import com.example.cowboyz.ui.components.ProductCard

@Composable
fun CategoryProductsScreen(
    navController: NavController,
    mainCategory: String,
    subCategory: String
) {
    val filteredProducts = SampleProducts.productList.filter {
        it.category.equals(mainCategory, ignoreCase = true) &&
                it.subCategory.equals(subCategory, ignoreCase = true)
    }

    if (filteredProducts.isEmpty()) {
        androidx.compose.foundation.layout.Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "No products found in $subCategory",
                style = MaterialTheme.typography.titleLarge
            )
        }
        return
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(horizontal = 12.dp, vertical = 12.dp),
        contentPadding = PaddingValues(bottom = 100.dp, top = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item(span = { GridItemSpan(2) }) {
            Text(
                text = "$mainCategory - $subCategory",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        items(filteredProducts) { product ->
            ProductCard(
                product = product,
                onClick = {
                    navController.navigate(Routes.productDetails(product.id))
                }
            )
        }
    }
}