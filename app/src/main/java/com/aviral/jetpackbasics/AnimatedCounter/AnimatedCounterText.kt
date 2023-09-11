package com.aviral.jetpackbasics.AnimatedCounter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.aviral.jetpackbasics.ui.theme.JetpackBasicsTheme

class AnimatedCounterText : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackBasicsTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    var count by remember {
                        mutableStateOf(0)
                    }

                    AnimatedCounter(count = count, textStyle = MaterialTheme.typography.h1)

                    Button(onClick = { count++ }) {
                        Text(text = "Increment")
                    }
                    
                }
            }
        }
    }

    @Preview(showSystemUi = true)
    @Composable
    fun ShowPreview() {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            var count by remember {
                mutableStateOf(0)
            }

            AnimatedCounter(count = count, textStyle = MaterialTheme.typography.h1)

            Button(onClick = { count++ }) {
                Text(text = "Increment")
            }

        }
    }
}