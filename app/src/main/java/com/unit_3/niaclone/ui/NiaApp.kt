package com.unit_3.niaclone.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.unit_3.niaclone.ui.interests.InterestDetailView
import com.unit_3.niaclone.ui.navigation.BottomNavHost

@Composable
fun NiaApp(
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main") { BottomNavHost(navController) }
        composable("interestDetail/{interestName}") { backStackEntry ->
            val interestName = backStackEntry.arguments?.getString("interestName") ?: ""
            InterestDetailView(interest = interestName)
        }
    }
}
