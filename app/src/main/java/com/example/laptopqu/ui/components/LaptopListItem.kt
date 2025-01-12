package com.example.laptopqu.ui.components

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.SearchBar as ComposeSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.laptopqu.LaptopQUViewModel
import com.example.laptopqu.R

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LaptopList(
    viewModel: LaptopQUViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val groupedLaptops by viewModel.groupedLaptops.collectAsState()
    val query by viewModel.query

    val filteredLaptops = groupedLaptops.filter {
        it.value.any { laptop ->
            laptop.name.contains(query, ignoreCase = true)
        }
    }

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        // Search bar sebagai sticky header
        stickyHeader {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(16.dp)
            ) {
                SearchBar(
                    query = query,
                    onQueryChange = viewModel::search,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }
        }

        // Menampilkan pesan jika tidak ada data yang ditemukan
        if (filteredLaptops.isEmpty()) {
            item {
                Text(
                    text = "Laptop yang Anda cari tidak ditemukan.",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

        // Menampilkan laptop jika ada hasil pencarian
        filteredLaptops.forEach { (_, laptops) ->
            items(laptops, key = { it.id }) { laptop ->
                LaptopListItem(
                    name = laptop.name,
                    photoUrl = laptop.photoUrl,
                    price = laptop.price,
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateItemPlacement(tween(durationMillis = 100))
                )
            }
        }
    }
}



@Composable
fun LaptopListItem(
    name: String,
    photoUrl: Int,
    price: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.clickable { }
    ) {
        Image(
            painter = painterResource(id = photoUrl),
            contentDescription = null,
            modifier = Modifier
                .padding(8.dp)
                .size(60.dp)
                .clip(CircleShape)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(start = 16.dp)
        ) {
            Text(
                text = name,
                fontWeight = FontWeight.Medium,
            )
            Text(
                text = price,
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    ComposeSearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = {},
        active = false,
        onActiveChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        placeholder = {
            Text("Cari Laptop Kesukaan Anda...!")
        },
        shape = MaterialTheme.shapes.large,
        modifier = modifier,
        content = {} // Empty content
    )
}


@Preview(showBackground = true)
@Composable
fun LaptopListItemPreview() {
    LaptopListItem(
        name = "Laptop XYZ",
        photoUrl = R.drawable.acernitro5,
        price = "Rp 12.000.000"
    )
}
