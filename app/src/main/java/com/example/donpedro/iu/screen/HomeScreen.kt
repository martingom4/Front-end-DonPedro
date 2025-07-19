package com.example.donpedro.iu.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.donpedro.navigation.AppScreens
import com.example.donpedro.ui.theme.PrimaryRed
import com.example.donpedro.ui.theme.SecondarySalmon
import com.example.donpedro.ui.theme.TertiaryCream

@Composable
fun HomeScreen(navController: NavController) {
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
                Text("Hi, Martin", color = PrimaryRed, fontSize = 24.sp)
                Text("Get your favorite food here!", color = SecondarySalmon)

                Spacer(modifier = Modifier.height(16.dp))

                HeroCarousel()
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
                    .clip(MaterialTheme.shapes.medium)
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