package com.aviral.jetpackbasics.bestPractices.customizingTheme

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.aviral.jetpackbasics.bestPractices.customizingTheme.ui.LocalSpacing
import com.aviral.jetpackbasics.bestPractices.customizingTheme.ui.spacing
import com.aviral.jetpackbasics.bestPractices.customizingTheme.ui.theme.JetpackBasicsTheme

class CustomizingTheme : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackBasicsTheme {
                Surface(
                    color = MaterialTheme.colors.background,
                    modifier = Modifier.padding(
                        MaterialTheme.spacing.medium
//                        We can also do it in this way
//                        LocalSpacing.current.medium
                    )
                ) {
//                    LocalSpacing.current.medium
                    MaterialTheme.spacing.medium
                }
            }
        }
    }
}