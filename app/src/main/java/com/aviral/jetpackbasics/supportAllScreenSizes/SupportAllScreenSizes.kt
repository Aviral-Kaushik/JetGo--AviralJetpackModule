package com.aviral.jetpackbasics.supportAllScreenSizes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aviral.jetpackbasics.ui.theme.JetpackBasicsTheme

class SupportAllScreenSizes : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackBasicsTheme {

                val windowInfo = RememberWindowInfo()

                if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(10) {
                            Text(
                                text = "Item $it",
                                fontSize = 25.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.Cyan)
                                    .padding(16.dp)
                            )
                        }

                        items(10) {
                            Text(
                                text = "Item $it",
                                fontSize = 25.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color.Green)
                                    .padding(16.dp)
                            )
                        }
                    }
                } else {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        LazyColumn(
                            modifier = Modifier.weight(1f)
                        ) {
                            items(10) {
                                Text(
                                    text = "Item $it",
                                    fontSize = 25.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color.Cyan)
                                        .padding(16.dp)
                                )
                            }
                        }

                        LazyColumn(
                            modifier = Modifier.weight(1f)
                        ) {
                            items(10) {
                                Text(
                                    text = "Item $it",
                                    fontSize = 25.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color.Green)
                                        .padding(16.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}