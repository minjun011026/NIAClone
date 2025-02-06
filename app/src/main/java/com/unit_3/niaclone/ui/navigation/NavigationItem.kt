package com.unit_3.niaclone.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title : String,
    val selectedIcon : ImageVector,
    val unselectedIcon : ImageVector
)

sealed class ScreenTitle(val title: String) {
    data object Home : ScreenTitle("Now In Android")
    data object Saved : ScreenTitle("Saved")
    data object Interests : ScreenTitle("Interests")

    companion object {
        fun fromIndex(index: Int): String {
            return when (index) {
                0 -> Home.title
                1 -> Saved.title
                2 -> Interests.title
                else -> throw IllegalArgumentException("Invalid index: $index")
            }
        }
    }
}