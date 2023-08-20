package com.aviral.jetpackbasics.multiSelectLazyColumn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aviral.jetpackbasics.ui.theme.JetpackBasicsTheme

class MultiSelectLazyColumn : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackBasicsTheme {
                var items by remember {
                    mutableStateOf(
                        (1..10).map {
                            ListItem(
                                title = "Item $it",
                                isSelected = false
                            )
                        }
                    )
                }

                /**
                 * If we want to pass all the selected items to a new activity then we have to use thi code block
                 * items.filter { items.isSelected }
                 * This will filter out the selected items in and add them to items array.
                 * Now we can pass items as an parameter to new composable
                 * */

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(items.size) { index ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    items = items.mapIndexed { i, item ->
                                        if (index == i) {
                                            item.copy(isSelected = !item.isSelected)
                                        } else item
                                    }
                                }
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(text = items[index].title)
                            if (items[index].isSelected) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "Selected",
                                    tint = Color.Green,
                                    modifier = Modifier.size(20.dp)
                                )
                            }

                        }
                    }
                }

            }
        }
    }

    @Preview(showSystemUi = true)
    @Composable
    fun Preview() {
        var items by remember {
            mutableStateOf(
                (1..10).map {
                    ListItem(
                        title = "Item $it",
                        isSelected = false
                    )
                }
            )
        }

        /**
         * If we want to pass all the selected items to a new activity then we have to use thi code block
         * items.filter { items.isSelected }
         * This will filter out the selected items in and add them to items array.
         * Now we can pass items as an parameter to new composable
         * */

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(items.size) { index ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            items = items.mapIndexed { i, item ->
                                if (index == i) {
                                    item.copy(isSelected = !item.isSelected)
                                } else item
                            }
                        }
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(text = items[index].title)
                    if (items[index].isSelected) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Selected",
                            tint = Color.Green,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                }
            }
        }

    }
}