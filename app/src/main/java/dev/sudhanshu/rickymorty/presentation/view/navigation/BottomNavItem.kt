package dev.sudhanshu.rickymorty.presentation.view.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(var title: String, var icon: ImageVector, var route: String) {
    data object Home : BottomNavItem("Home", Icons.Default.Home, "home")
    data object Favourite : BottomNavItem("Favourite", Icons.Default.Favorite, "favourite")
}