package com.example.netflix.uix.views

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.media3.exoplayer.offline.Download
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.netflix.data.entity.Netflix
import com.google.gson.Gson

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf("Ana Sayfa", "Prime", "Begenilen", "Arama", "Bana Özel")
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(
        containerColor = Color.Black, // Koyu arka plan rengi
        contentColor = Color.White,   // Yazı ve ikonlar için açık renk
    ) {
        items.forEach { item ->
            NavigationBarItem(
                label = { Text(item, color = Color.White) },  // Koyu renk ile uyumlu yazı rengi
                selected = currentRoute == item.toLowerCase(),
                onClick = {
                    when (item) {
                        "Ana Sayfa" -> navController.navigate("anasayfa") {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                        "Prime" -> navController.navigate("primePage") {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                        "Beğenilenler" -> navController.navigate("favoritePage") {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                        "Arama" -> navController.navigate("searchPage") {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                        "Bana Özel" -> navController.navigate("banaOzelPage") {
                            popUpTo(navController.graph.startDestinationId)
                            launchSingleTop = true
                        }
                    }
                },
                icon = {
                    when (item) {
                        "Ana Sayfa" -> Icon(Icons.Default.Home, contentDescription = null, tint = Color.White)
                        "Prime" -> Icon(Icons.Default.Star, contentDescription = null, tint = Color.White)
                        "Begenilen" -> Icon(Icons.Default.Favorite, contentDescription = null, tint = Color.White)
                        "Arama" -> Icon(Icons.Default.Search, contentDescription = null, tint = Color.White)
                        "Bana Özel" -> Icon(Icons.Default.Person, contentDescription = null, tint = Color.White)
                    }
                }
            )
        }
    }
}

@Composable
fun SayfaGecisleri() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "anasayfa",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("anasayfa") {
                Anasayfa(navController)
            }
            composable("primePage") {
                // Prime sayfası içeriği buraya gelecek
                Text(text = "Prime İçerikleri", color = Color.White, modifier = Modifier.padding(16.dp))
            }
            composable("favoritePage") {
                // İndirilenler sayfası içeriği buraya gelecek
                Text(text = "Begenilen", color = Color.White, modifier = Modifier.padding(16.dp))
            }
            composable("searchPage") {
                // Arama sayfası içeriği buraya gelecek
                Text(text = "Arama", color = Color.White, modifier = Modifier.padding(16.dp))
            }
            composable("banaOzelPage") {
                // Bana Özel sayfası içeriği buraya gelecek
                Text(text = "Bana Özel İçerikler", color = Color.White, modifier = Modifier.padding(16.dp))
            }
            composable(
                "detaySayfa/{film}",
                arguments = listOf(navArgument("film") {
                    type = NavType.StringType
                })
            ) {
                val json = it.arguments?.getString("film")
                val nesne = Gson().fromJson(json, Netflix::class.java)
                DetaySayfa(nesne)
            }
        }
    }
}
