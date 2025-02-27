package com.unit_3.niaclone.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Tag
import androidx.compose.material.icons.filled.Upcoming
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material.icons.outlined.Tag
import androidx.compose.material.icons.outlined.Upcoming
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.unit_3.niaclone.ui.navigation.component.TopBar
import com.unit_3.niaclone.ui.foryou.ForYouView
import com.unit_3.niaclone.ui.interests.InterestsView
import com.unit_3.niaclone.ui.saved.SavedView

@Composable
fun BottomNavHost(navController: NavHostController) {
    val items = listOf(
        NavigationItem(
            title = "ForYou",
            selectedIcon = Icons.Filled.Upcoming,
            unselectedIcon = Icons.Outlined.Upcoming
        ),
        NavigationItem(
            title = "Saved",
            selectedIcon = Icons.Filled.Bookmarks,
            unselectedIcon = Icons.Outlined.Bookmarks
        ),
        NavigationItem(
            title = "Interests",
            selectedIcon = Icons.Filled.Tag,
            unselectedIcon = Icons.Outlined.Tag
        )
    )

    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = { TopBar(ScreenTitle.fromIndex(selectedItemIndex)) },
            bottomBar = {
                NavigationBar {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedItemIndex == index,
                            onClick = {
                                selectedItemIndex = index
                            },
                            label = {
                                Text(text = item.title)
                            },
                            icon = {
                                Icon(
                                    imageVector = if (index == selectedItemIndex) item.selectedIcon else item.unselectedIcon,
                                    contentDescription = item.title
                                )
                            }
                        )
                    }
                }
            }
        ) { innerPadding ->
            ContentScreen(modifier = Modifier.padding(innerPadding), selectedItemIndex, navController)
        }
    }
}

@Composable
fun ContentScreen(modifier: Modifier = Modifier, selectedIndex: Int, navController: NavHostController) {
    when (selectedIndex) {
        0 -> ForYouView(modifier)
        1 -> SavedView(modifier)
        2 -> InterestsView(modifier = modifier, navController = navController)
    }
}
