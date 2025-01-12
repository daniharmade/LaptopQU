package com.example.laptopqu.ui.screen.favorite

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.laptopqu.LaptopQUViewModel
import com.example.laptopqu.R
import com.example.laptopqu.ui.components.LaptopListItem

@Composable
fun FavoriteScreen(
    viewModel: LaptopQUViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val favoriteLaptops = viewModel.favoriteLaptops.collectAsState().value

    if (favoriteLaptops.isEmpty()) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(R.string.no_favorites),
                modifier = Modifier.padding(16.dp)
            )
        }
    } else {
        LazyColumn(
            modifier = modifier.padding(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(favoriteLaptops, key = { it.id }) { laptop ->
                LaptopListItem(
                    laptopId = laptop.id,
                    name = laptop.name,
                    photoUrl = laptop.photoUrl,
                    price = laptop.price,
                    isFavorite = laptop.isFavorite,
                    navController = navController,
                    onFavoriteClick = { id ->
                        viewModel.toggleFavorite(id) // Update favorite status
                    }
                )
            }
        }
    }
}
