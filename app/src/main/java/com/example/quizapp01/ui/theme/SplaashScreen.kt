package com.example.quizapp01.ui.theme



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.quizapp01.R
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)





        setContent {


            QuizApp01Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }


            }
        }
    }
}









@Composable
fun SplashScreen(onTimeOut:() ->Unit,modifier: Modifier = Modifier ) {
    val image = painterResource(R.drawable.pngegg_1_)
    Surface(color = Color(0xFF9AB2B8), modifier = modifier
        .fillMaxSize()

    )
    {
    }

    Box(modifier = modifier)
    Image(painter = image, alignment = Alignment.Center,
        contentDescription = null,
        modifier = modifier.padding(
            vertical = 200.dp,
            horizontal = 22.dp
        )
    )
LaunchedEffect(true )
{
    delay(500)
    onTimeOut.invoke()
}

}











