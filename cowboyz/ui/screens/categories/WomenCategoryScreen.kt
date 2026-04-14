package com.example.cowboyz.ui.screens.categories

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.cowboyz.R
import com.example.cowboyz.navigation.Routes

data class WomenSubCategory(
    val title: String,
    val imageRes: Int
)

@Composable
fun WomenCategoryScreen(navController: NavController) {
    val womenCategories = listOf(
        WomenSubCategory("Dresses", R.drawable.cat_dresses),
        WomenSubCategory("Kurtas", R.drawable.cat_kurtas),
        WomenSubCategory("Top Wear", R.drawable.cat_topwear),
        WomenSubCategory("Bottoms", R.drawable.cat_bottoms)
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        contentPadding = PaddingValues(bottom = 100.dp, top = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item(span = { androidx.compose.foundation.lazy.grid.GridItemSpan(2) }) {
            Text(
                text = "Women Categories",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        items(womenCategories) { item ->
            Card(
                modifier = Modifier.clickable {
                    navController.navigate(
                        Routes.categoryProducts("Women", item.title)
                    )
                },
                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                androidx.compose.foundation.layout.Column(
                    modifier = Modifier.padding(12.dp)
                ) {
                    Image(
                        painter = painterResource(id = item.imageRes),
                        contentDescription = item.title,
                        modifier = Modifier
                            .height(150.dp)
                            .clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.Crop
                    )

                    Text(
                        text = item.title,
                        modifier = Modifier.padding(top = 12.dp),
                        style = MaterialTheme.typography.titleMedium
                    )
                }
            }
        }
    }
}