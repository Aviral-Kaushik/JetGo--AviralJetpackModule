package com.aviral.jetpackbasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aviral.jetpackbasics.ui.theme.JetpackBasicsTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
class BottomSheet : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackBasicsTheme {

                val sheetState = rememberBottomSheetState(
                    initialValue = BottomSheetValue.Collapsed
                )

                val scaffoldState = rememberBottomSheetScaffoldState(
                    bottomSheetState = sheetState
                )

                val scope = rememberCoroutineScope()

                BottomSheetScaffold(
                    scaffoldState = scaffoldState,
                    sheetContent = {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .height(300.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "Bottom Sheet", fontSize = 60.sp)
                        }
                    },
                    sheetBackgroundColor = Color.Green,
                    sheetPeekHeight = 3.dp
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Button(onClick = {
                            scope.launch {
                                if (sheetState.isExpanded) {
                                    sheetState.collapse()
                                } else {
                                    sheetState.expand()
                                }
                            }
                        }) {
                            Text(text = "Toggle Bottom Sheet")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PreviewBottomSheet() {
    val sheetState = rememberBottomSheetState(
        initialValue = BottomSheetValue.Collapsed
    )

    val scaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = sheetState
    )

    val scope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .height(70.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Bottom Sheet", fontSize = 60.sp)
            }
        },
        sheetBackgroundColor = Color.Green,
        sheetPeekHeight = 3.dp
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = {
                scope.launch {
                    if (sheetState.isExpanded) {
                        sheetState.collapse()
                    } else {
                        sheetState.expand()
                    }
                }
            }) {
                Text(text = "Toggle Bottom Sheet")
            }
        }
    }
}
