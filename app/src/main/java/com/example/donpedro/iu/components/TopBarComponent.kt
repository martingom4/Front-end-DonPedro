package com.example.donpedro.iu.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.Modifier
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import com.example.donpedro.navigation.AppScreens
import com.example.donpedro.ui.theme.PrimaryRed


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarComponent(
    title: String,
    onBackClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )
        },
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Arrow Back",
                modifier = Modifier.clickable { onBackClick()}
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent,
            titleContentColor = PrimaryRed,
            navigationIconContentColor = PrimaryRed
        )
    )
}