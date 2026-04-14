package com.example.cowboyz.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cowboyz.R
import com.example.cowboyz.data.sample.SampleProducts
import com.example.cowboyz.navigation.Routes
import com.example.cowboyz.ui.components.HomeCategoryItem
import com.example.cowboyz.ui.components.OfferBadge
import com.example.cowboyz.ui.components.ProductCard
import com.example.cowboyz.ui.components.SearchBar

data class HomeCategory(
    val title: String,
    val imageRes: Int
)

@Composable
fun HomeScreen(navController: NavController) {
    val query = remember { mutableStateOf("") }

    val filteredProducts = SampleProducts.productList.filter {
        it.name.contains(query.value, ignoreCase = true) ||
                it.category.contains(query.value, ignoreCase = true) ||
                it.subCategory.contains(query.value, ignoreCase = true)
    }

    val homeCategories = listOf(
        HomeCategory("Men", R.drawable.cat_men),
        HomeCategory("Women", R.drawable.cat_women),
        HomeCategory("Kids", R.drawable.cat_kids)
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentPadding = PaddingValues(bottom = 90.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {
            Text(
                text = "CowBoyz Official Store",
                style = MaterialTheme.typography.headlineMedium
            )
        }

        item {
            SearchBar(
                query = query.value,
                onQueryChange = { query.value = it }
            )
        }

        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(homeCategories) { category ->
                    HomeCategoryItem(
                        title = category.title,
                        imageRes = category.imageRes,
                        onClick = {
                            when (category.title) {
                                "Men" -> navController.navigate(Routes.MEN_CATEGORIES)
                                "Women" -> navController.navigate(Routes.WOMEN_CATEGORIES)
                            }
                        }
                    )
                }
            }
        }

        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(190.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                ),
                shape = RoundedCornerShape(20.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "CowBoyz Collection",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSecondary
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Modern western fashion for bold style",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSecondary
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    OfferBadge("HOT SALE 🔥")
                }
            }
        }

        item {
            Text(
                text = "Trending Now",
                style = MaterialTheme.typography.titleLarge
            )
        }

        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(filteredProducts) { product ->
                    ProductCard(
                        product = product,
                        onClick = {
                            navController.navigate(
                                Routes.productDetails(product.id)
                            )
                        }
                    )
                }
            }
        }
    }
}