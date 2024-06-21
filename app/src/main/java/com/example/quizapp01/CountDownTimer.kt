package com.example.quizapp01

import android.os.CountDownTimer
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CountdownTimer(minutes: Long, seconds: Long) {
    var timerRunning by remember { mutableStateOf(false) }
    var timeRemaining by remember { mutableLongStateOf((minutes * 60 + seconds) * 1000L) }

    var countDownTimer: CountDownTimer? by remember { mutableStateOf(null) }

    var minuteText by remember { mutableStateOf(minutes.toString()) }
    var secondText by remember { mutableStateOf(seconds.toString()) }

    var keyboardController by remember { mutableStateOf<SoftwareKeyboardController?>(null) }
    val softwareKeyboardController = LocalSoftwareKeyboardController.current

    val textInputService = LocalTextInputService.current

    var timeInputFocused by remember { mutableStateOf(false) }

    fun startTimer(timeInMillis: Long) {
        countDownTimer = object : CountDownTimer(timeInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeRemaining = millisUntilFinished
            }

            override fun onFinish() {
                timerRunning = false
            }
        }.start()
    }




    fun resetTimer() {
        countDownTimer?.cancel()
        timerRunning = false
        timeRemaining = (minuteText.toLong() * 60 + secondText.toLong()) * 1000L
    }


    DisposableEffect(softwareKeyboardController) {
        keyboardController = softwareKeyboardController
        onDispose {
            keyboardController = null
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = minuteText,
                onValueChange = {
                    if (!timerRunning) {
                        minuteText = it
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    capitalization = KeyboardCapitalization.None
                ),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colorScheme.background)
                    .padding(8.dp)
                    .clickable {
                        textInputService?.showSoftwareKeyboard()
                    }
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = secondText,
                onValueChange = {
                    if (!timerRunning) {
                        secondText = it
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    capitalization = KeyboardCapitalization.None
                ),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(MaterialTheme.colorScheme.background)
                    .padding(8.dp)
                    .clickable {
                        textInputService?.showSoftwareKeyboard()
                    }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        if (timeRemaining > 0) {
                            if (timerRunning) {
                                countDownTimer?.cancel()
                            } else {
                                startTimer(timeRemaining)
                            }
                            timerRunning = !timerRunning
                        }
                    },
                    enabled = timeRemaining > 0,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(if (timerRunning) "Pause" else "Start")
                }

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = {
                        if (!timerRunning) {
                            resetTimer()
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text("Reset")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))


        }
    }
        // Detect focus changes on text input fields and close the keyboard accordingly


    @Composable
    fun formatTime(timeInMillis: Long): String {
        @Suppress("NAME_SHADOWING") val minutes = (timeInMillis / 1000) / 60
        val seconds = (timeInMillis / 1000) % 60
        return "%02d:%02d".format(minutes, seconds)
    }


}

@Composable
fun TimerScreen() {
    CountdownTimer(minutes = 5, seconds = 0)
}

@Composable
fun TimerApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        TimerScreen()
    }
}

@Composable
fun CountdownTimerApp() {
    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(
            primary = Color(0xFF1976D2),
            onPrimary = Color.White
        )
    ) {
        TimerApp()
    }
}