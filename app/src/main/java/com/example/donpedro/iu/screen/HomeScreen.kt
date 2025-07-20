package com.example.donpedro.iu.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.donpedro.data.local.SessionManager
import com.example.donpedro.viewmodel.HomeViewModel
import com.example.donpedro.data.local.Product
import com.example.donpedro.navigation.AppScreens
import com.example.donpedro.ui.theme.PrimaryRed
import com.example.donpedro.ui.theme.SecondarySalmon
import com.example.donpedro.ui.theme.TertiaryCream
import com.example.donpedro.ui.theme.NeutralGrey



@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel) {
    val context = LocalContext.current
    val sessionManager = remember { SessionManager(context) }
    val userName by sessionManager.getUserName().collectAsState(initial = null)

    val products by viewModel.products.collectAsState()
    val bgGradient = Brush.verticalGradient(
        colors = listOf(TertiaryCream, Color.White)
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
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Hi, $userName", color = PrimaryRed, fontSize = 24.sp)
                Text("Get your favorite food here!", color = SecondarySalmon)

                Spacer(modifier = Modifier.height(16.dp))

                HeroCarousel()
                Spacer(modifier = Modifier.height(24.dp))
                ProductList(products)
            }
        }
    }
}

@Composable
fun ProductList(products: List<Product>) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(products) { product ->
            Card(
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, PrimaryRed),
                elevation = CardDefaults.cardElevation(2.dp),
                colors = CardDefaults.cardColors(containerColor = TertiaryCream),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(Modifier.padding(12.dp)) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(product.imageUrl)
                            .crossfade(true)
                            .placeholder(android.R.color.transparent)
                            .build(),
                        contentDescription = product.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )
                    Spacer(Modifier.width(12.dp))
                    Column(Modifier.weight(1f)) {
                        Text(
                            product.name,
                            color = PrimaryRed,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 15.sp
                        )
                        Text(
                            product.description,
                            color = NeutralGrey,
                            fontSize = 12.sp,
                            maxLines = 2
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            product.price,
                            color = SecondarySalmon,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun HeroCarousel() {
    val items = listOf("Promo 1", "Promo 2", "Promo 3")
    LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        items(items) { item ->
            Box(
                modifier = Modifier
                    .width(250.dp)
                    .height(140.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(PrimaryRed),
                contentAlignment = Alignment.Center
            ) {
                Text(item, color = Color.White, fontSize = 18.sp)
            }
        }
    }
}

@Composable
fun BottomBar(navController: NavController) {
    NavigationBar(containerColor = Color.White) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = true,
            onClick = {}
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Search, contentDescription = "Search") },
            label = { Text("Search") },
            selected = false,
            onClick = { }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            label = { Text("Profile") },
            selected = false,
            onClick = {navController.navigate(route = AppScreens.ProfileScreen.route) }
        )
    }
}