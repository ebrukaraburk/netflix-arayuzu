package com.example.netflix.uix.views

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.netflix.data.entity.Netflix
import com.google.gson.Gson

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Anasayfa(navController: NavController) {
    val filmlerListesi = remember { mutableStateListOf<Netflix>() }
    val esaretListesi = remember { mutableStateListOf<Netflix>() }
    val dizi = remember { mutableStateListOf<Netflix>() }
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = true) {
        val f1 = Netflix(1, "prisonbreak", "prisonbreak")
        val f2 = Netflix(2, "kul", "kul")
        val f3 = Netflix(3, "sahmaran", "sahmaran")
        val f4 = Netflix(4, "ask", "ask")
        val f5 = Netflix(5, "young", "young")
        val f6 = Netflix(6, "Wednesday", "wednesday")
        val f7 = Netflix(7, "You", "you")

        filmlerListesi.addAll(listOf(f1, f2, f3, f4, f5, f6, f7))

        val e1 = Netflix(8, "Esaret", "esaret")
        val e2 = Netflix(9, "Ayla", "ayla")
        val e3 = Netflix(10, "Forgotton", "forgotton")
        val e4 = Netflix(11, "Pandora", "pandora")
        val e5 = Netflix(12, "Good Place", "goodplace")
        val e6 = Netflix(13, "strangerthings", "strangerthings")

        esaretListesi.addAll(listOf(e1, e2, e3, e4, e5, e6))

        val d1 = Netflix(1, "Anna", "anna")
        val d2 = Netflix(2, "Birdbox", "birdbox")
        val d3 = Netflix(3, "Friends", "friends")
        val d4 = Netflix(4, "Patron Bebek", "patronbebek")
        val d5 = Netflix(5, "Sweethome", "sweethome")
        val d6 = Netflix(6, "Wednesday", "wednesday")
        val d7 = Netflix(7, "You", "you")
        dizi.addAll(listOf(d1, d2, d3, d4, d5, d6, d7))
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier.padding(start = 2.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text(text = "Netflix", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
                            Text(text = "Ana Sayfa", fontSize = 10.sp, color = Color.White)
                            Text(text = "Yeni Diziler", fontSize = 10.sp, color = Color.White)
                            Text(text = "Diziler", fontSize = 10.sp, color = Color.White)
                            Text(text = "Filmler", fontSize = 10.sp, color = Color.White)
                        }
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Black)
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.Black)
        ) {
            // Diziler Listesi
            Text(
                text = "En Çok İzlenen Diziler",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                items(dizi) { film ->
                    Card(
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                            .width(70.dp)
                            .height(100.dp),
                        elevation = CardDefaults.elevatedCardElevation(8.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable {
                                    val filmJson = Gson().toJson(film)
                                    navController.navigate("detaySayfa/$filmJson")
                                }
                        ) {
                            val activity = (LocalContext.current as Activity)
                            Image(
                                bitmap = ImageBitmap.imageResource(
                                    id = activity.resources.getIdentifier(
                                        film.resim, "drawable", activity.packageName
                                    )
                                ),
                                contentDescription = film.ad,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(8.dp))
                            )
                        }
                    }
                }
            }

            // Filmler Listesi
            Text(
                text = "Sıradaki Önerimiz",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                items(filmlerListesi) { film ->
                    Card(
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                            .width(70.dp)
                            .height(100.dp),
                        elevation = CardDefaults.elevatedCardElevation(8.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable {
                                    val filmJson = Gson().toJson(film)
                                    navController.navigate("detaySayfa/$filmJson")
                                }
                        ) {
                            val activity = (LocalContext.current as Activity)
                            Image(
                                bitmap = ImageBitmap.imageResource(
                                    id = activity.resources.getIdentifier(
                                        film.resim, "drawable", activity.packageName
                                    )
                                ),
                                contentDescription = film.ad,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(8.dp))
                            )
                        }
                    }
                }
            }

            // Filmler Listesi
            Text(
                text = "İzlemeye Devam Et",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                items(esaretListesi) { film ->
                    Card(
                        modifier = Modifier
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                            .width(70.dp)
                            .height(100.dp),
                        elevation = CardDefaults.elevatedCardElevation(8.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable {
                                    val filmJson = Gson().toJson(film)
                                    navController.navigate("detaySayfa/$filmJson")
                                }
                        ) {
                            val activity = (LocalContext.current as Activity)
                            Image(
                                bitmap = ImageBitmap.imageResource(
                                    id = activity.resources.getIdentifier(
                                        film.resim, "drawable", activity.packageName
                                    )
                                ),
                                contentDescription = film.ad,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(8.dp))
                            )
                        }
                    }
                }
            }
        }
    }
}
