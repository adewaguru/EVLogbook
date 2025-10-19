package com.asanga.evlogbook.ui.nav

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.BatteryChargingFull
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.asanga.evlogbook.ui.screens.about.AboutScreen
import com.asanga.evlogbook.ui.screens.charge.AddEditChargeScreen
import com.asanga.evlogbook.ui.screens.charge.ChargeListScreen
import com.asanga.evlogbook.ui.screens.home.HomeScreen
import com.asanga.evlogbook.ui.screens.reports.ReportsScreen
import com.asanga.evlogbook.ui.screens.settings.SettingsScreen
import com.asanga.evlogbook.ui.screens.trip.AddEditTripScreen
import com.asanga.evlogbook.ui.screens.trip.TripListScreen

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomNavItem("home", "Home", Icons.Default.Home)
    object Trips : BottomNavItem("trips", "Trips", Icons.Default.DirectionsCar)
    object Charges : BottomNavItem("charges", "Charges", Icons.Default.BatteryChargingFull)
    object Reports : BottomNavItem("reports", "Reports", Icons.Default.Assessment)
    object Settings : BottomNavItem("settings", "Settings", Icons.Default.Settings)
}

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController()
) {
    val bottomNavItems = listOf(
        BottomNavItem.Home,
        BottomNavItem.Trips,
        BottomNavItem.Charges,
        BottomNavItem.Reports,
        BottomNavItem.Settings
    )

    Scaffold(
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination
            
            // Only show bottom nav on main screens
            if (currentDestination?.route in bottomNavItems.map { it.route }) {
                NavigationBar {
                    bottomNavItems.forEach { item ->
                        NavigationBarItem(
                            icon = { Icon(item.icon, contentDescription = item.title) },
                            label = { Text(item.title) },
                            selected = currentDestination?.hierarchy?.any { it.route == item.route } == true,
                            onClick = {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("home") {
                HomeScreen(
                    onNavigateToAddTrip = { navController.navigate("add_trip") },
                    onNavigateToAddCharge = { navController.navigate("add_charge") }
                )
            }

            composable("trips") {
                TripListScreen(
                    onNavigateToAddTrip = { navController.navigate("add_trip") },
                    onNavigateToEditTrip = { tripId ->
                        navController.navigate("edit_trip/$tripId")
                    }
                )
            }

            composable("add_trip") {
                AddEditTripScreen(
                    onNavigateBack = { navController.popBackStack() }
                )
            }

            composable("edit_trip/{tripId}") { backStackEntry ->
                val tripId = backStackEntry.arguments?.getString("tripId")?.toLongOrNull() ?: 0L
                AddEditTripScreen(
                    tripId = tripId,
                    onNavigateBack = { navController.popBackStack() }
                )
            }

            composable("charges") {
                ChargeListScreen(
                    onNavigateToAddCharge = { navController.navigate("add_charge") },
                    onNavigateToEditCharge = { chargeId ->
                        navController.navigate("edit_charge/$chargeId")
                    }
                )
            }

            composable("add_charge") {
                AddEditChargeScreen(
                    onNavigateBack = { navController.popBackStack() }
                )
            }

            composable("edit_charge/{chargeId}") { backStackEntry ->
                val chargeId = backStackEntry.arguments?.getString("chargeId")?.toLongOrNull() ?: 0L
                AddEditChargeScreen(
                    chargeId = chargeId,
                    onNavigateBack = { navController.popBackStack() }
                )
            }

            composable("reports") {
                ReportsScreen()
            }

            composable("settings") {
                SettingsScreen(
                    onNavigateToAbout = { navController.navigate("about") }
                )
            }

            composable("about") {
                AboutScreen(
                    onNavigateBack = { navController.popBackStack() }
                )
            }
        }
    }
}
