package com.example.donpedro.iu.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.donpedro.ui.theme.PrimaryRed
import com.example.donpedro.ui.theme.SecondarySalmon
import com.example.donpedro.ui.theme.TertiaryCream
import com.example.donpedro.viewmodel.HomeViewModel

@Composable
fun SearchProductsScreen(
    navController: NavController,
    viewModel: HomeViewModel
) {
    val allProducts by viewModel.products.collectAsState()
    var query by remember { mutableStateOf("") }

    val filtered = remember(query, allProducts) {
        if (query.isBlank()) allProducts
        else allProducts.filter {
            it.name.startsWith(query, ignoreCase = true)
        }
    }

    val bgGradient = Brush.verticalGradient(
        colors = listOf(TertiaryCream, Color.White),
        tileMode = TileMode.Clamp
    )

    Scaffold(
        containerColor = Color.Transparent,
        bottomBar = { BottomBar(navController) }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(bgGradient)
                .padding(padding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Card(
                    shape = RoundedCornerShape(32.dp),
                    elevation = CardDefaults.cardElevation(0.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    TextField(
                        value = query,
                        onValueChange = { query = it },
                        placeholder = { Text("Buscar producto...", color = SecondarySalmon) },
                        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = PrimaryRed) },
                        singleLine = true,
                        shape = RoundedCornerShape(32.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            focusedIndicatorColor = PrimaryRed,
                            unfocusedIndicatorColor = PrimaryRed,
                            disabledIndicatorColor = PrimaryRed,
                            cursorColor = PrimaryRed
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Box(Modifier.padding(horizontal = 16.dp)) {
                    ProductList(products = filtered)
                }
            }
        }
    }
}