package com.aviral.jetpackbasics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.aviral.jetpackbasics.ui.theme.JetpackBasicsTheme

class ComplexAnimations : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackBasicsTheme {

                var progress by remember {
                    mutableStateOf(0F)
                }

                Column {
                    ProfileHeader(progress = progress)

                    Spacer(modifier = Modifier.height(32.dp))

                    Slider(value = progress, onValueChange = {
                        progress = it
                    }, modifier = Modifier.padding(32.dp))
                }
            }
        }
    }
}


@Composable
fun ProfileHeader(progress: Float) {
    val context = LocalContext.current
    val motionScene = remember {
        context.resources
            .openRawResource(R.raw.motion_scene)
            .readBytes()
            .decodeToString()
    }

    MotionLayout(
        motionScene = MotionScene(content = motionScene),
        progress = progress,
        modifier = Modifier.fillMaxSize()
    ) {

        val properties = motionProperties(id = "profile_pic")

        Box(
            modifier = Modifier
                .fillMaxWidth(0.3f)
                .background(Color.DarkGray)
                .layoutId("box")
        )

        Image(
            painter = painterResource(id = R.drawable.aviral_kaushik),
            contentDescription = "profile_pic",
            modifier = Modifier
                .clip(CircleShape)
                .border(
                    width = 2.dp,
                    color = properties.value.color("background"),
                    shape = CircleShape
                )
                .layoutId("profile_pic")
        )

        Text(
            text = "Aviral Kaushik",
            fontSize = 24.sp,
            modifier = Modifier.layoutId("username"),
            color = properties.value.color("background")
        )
    }
}