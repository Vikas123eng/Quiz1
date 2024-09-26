package com.example.quizapp01.ui.theme.ui.login

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("SuspiciousIndentation")
@Composable
fun LeaderboardScreen(navController: NavController){

    var userPhotoUrl by remember { mutableStateOf<String?>(null) }
    val auth: FirebaseAuth = Firebase.auth

    LaunchedEffect(Unit) {
        val currentUser = auth.currentUser
        currentUser?.let {

            userPhotoUrl = it.photoUrl?.toString()
        }
    }

    val players = listOf(
        Player("#1", "Obida1990",userPhotoUrl, 1370),
        Player("#2", "FoxGamerF-1", userPhotoUrl, 362),
        Player("#3", "bishoyZakaria2023oooo", userPhotoUrl, 350),
        Player("#4", "smarta2005", userPhotoUrl, 210),
        Player("#5", "Maharaj2210AG", userPhotoUrl, 185)
    )




    Column (modifier =Modifier.fillMaxSize()){

        Column ( modifier = Modifier
                .fillMaxWidth()
             .background(color = Color(0xFFD9E7CB))
               .padding(16.dp)){
            CenterAlignedTopAppBar(
                colors = androidx.compose.material3.TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                ),
                title = {
                Text(
                text = "Rankings",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    textDecoration = TextDecoration.Underline
                )
            ) })

        }
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(color = Color(0xFFD9E7CB))
//                .padding(16.dp),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.Center) {
//            Spacer(modifier = Modifier.height(8.dp))
//            Text(
//                text = "Rankings",
//                style = TextStyle(
//                    color = Color.Black,
//                    fontSize = 20.sp,
//                    fontWeight = FontWeight.Bold,
//                    textDecoration = TextDecoration.Underline
//                )
//            )
//
//        }
      //  HorizontalDivider()

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            itemsIndexed(players) { index, player ->
                LeaderboardRow(player)
                HorizontalDivider(thickness = 1.dp, color = Color.Gray)
            }
        }
    }
    }

    @Composable
    fun LeaderboardRow(player: Player) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Rank
            Text(
                text = player.rank,
                modifier = Modifier.width(40.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = when (player.rank) {
                    "#1" -> Color(0xFFFFD700) // Gold for first place
                    "#2" -> Color(0xFFC0C0C0) // Silver for second place
                    "#3" -> Color(0xFFCD7F32) // Bronze for third place
                    else -> Color.Black
                }
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Avatar (use default if URL is empty)
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(data = player.userPhotoUrl)
                    .apply(block = fun ImageRequest.Builder.() {
                        crossfade(true)
                        size(51) // Scale down the image to fit the required size
                    }).build(),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .aspectRatio(1f)
                    .padding(4.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(16.dp))

            // Name and Country Flag
            Text(
                text = player.name,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                modifier = Modifier.weight(1f)
            )

            // Trophies
            Text(
                text = player.score.toString(),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }


data class Player(val rank: String, val name: String, val userPhotoUrl:String?, val score: Int)