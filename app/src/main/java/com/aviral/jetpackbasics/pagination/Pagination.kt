package com.aviral.jetpackbasics.pagination

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aviral.jetpackbasics.ui.theme.JetpackBasicsTheme

class Pagination : ComponentActivity() {

//    implementation "androidx.lifecycle:lifecycle-viewmodel-compose:2.4.1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackBasicsTheme {

                val viewModel = viewModel<PaginationViewModel>()
                val state = viewModel.state

                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {

                    items(state.items.size) {i ->
                        val item = state.items[i]

                        if (i >= state.items.size - 1 && !state.endReached && !state.isLoading) {
                            viewModel.loadNextItem()
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            
                            Text(text = item.title, fontSize = 20.sp, color = Color.Black)
                            
                            Spacer(modifier = Modifier.height(8.dp))

                            Text(text = item.description)
                            
                        }

                    }

                    item {
                        if (state.isLoading) {

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp),
                                horizontalArrangement = Arrangement.Center
                            ) {

                                CircularProgressIndicator()

                            }

                        }
                    }

                }

            }
        }
    }
}

