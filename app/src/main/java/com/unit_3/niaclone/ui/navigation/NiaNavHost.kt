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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.unit_3.niaclone.ui.navigation.component.TopBar
import com.unit_3.niaclone.ui.foryou.ForYouView
import com.unit_3.niaclone.ui.interestDetail.InterestDetailView
import com.unit_3.niaclone.ui.interests.InterestsView
import com.unit_3.niaclone.ui.navigation.component.Dest
import com.unit_3.niaclone.ui.saved.SavedView

@Composable
fun NiaNavHost() {

    val navController = rememberNavController()

    val items = listOf(
        NavigationItem(
            title = "ForYou",
            selectedIcon = Icons.Filled.Upcoming,
            unselectedIcon = Icons.Outlined.Upcoming,
            route = Dest.ForYou
        ),
        NavigationItem(
            title = "Saved",
            selectedIcon = Icons.Filled.Bookmarks,
            unselectedIcon = Icons.Outlined.Bookmarks,
            route = Dest.Saved
        ),
        NavigationItem(
            title = "Interests",
            selectedIcon = Icons.Filled.Tag,
            unselectedIcon = Icons.Outlined.Tag,
            route = Dest.Interests
        )
    )

    val currentBackEntry by navController.currentBackStackEntryAsState()
    var selectedItemIndex by remember { mutableIntStateOf(0) }
    val currentDestination = currentBackEntry?.destination

    LaunchedEffect(currentDestination) {
        selectedItemIndex = items.indexOfFirst { currentDestination?.hasRoute(it.route::class) == true }
            .takeIf { it != -1 } ?: 0
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
                                navController.navigate(item.route) {
                                    launchSingleTop = true
                                    restoreState = true
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                }
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
            NavHost(
                navController = navController,
                startDestination = Dest.ForYou
            ) {
                composable<Dest.ForYou> {
                    ForYouView(modifier = Modifier.padding(innerPadding))
                }
                composable<Dest.Saved> {
                    SavedView(modifier = Modifier.padding(innerPadding))
                }
                composable<Dest.Interests> {
                    InterestsView(
                        modifier = Modifier.padding(innerPadding),
                        navController = navController
                    )
                }
                composable<Dest.InterestDetail> {
                    val args = it.toRoute<Dest.InterestDetail>()
                    InterestDetailView(args.interest)
                }
            }
        }
    }
}