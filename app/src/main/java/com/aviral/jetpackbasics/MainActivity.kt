package com.aviral.jetpackbasics

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.aviral.jetpackbasics.bottomNavWithBadges.BottomNavItem
import com.aviral.jetpackbasics.bottomNavWithBadges.BottomNavWithBadges
import com.aviral.jetpackbasics.bottomNavWithBadges.BottomNavigationBar
import com.aviral.jetpackbasics.pagination.Pagination
import com.aviral.jetpackbasics.ui.theme.JetpackBasicsTheme
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackBasicsTheme {
                startActivity(Intent(this, Pagination::class.java))
            }
        }
    }
}


@Composable
fun JetpackConstraintLayout() {

    val constraint = ConstraintSet {

        val greenBox = createRefFor("greenBox")
        val redBox = createRefFor("redBox")
        val guideLine = createGuidelineFromTop(0.5f)

        constrain(greenBox) {
            top.linkTo(guideLine)
//                top.linkTo(parent.top)
            start.linkTo(parent.start)
//                width = Dimension.matchParent
            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }

        constrain(redBox) {
            top.linkTo(parent.top)
            start.linkTo(greenBox.end)
            end.linkTo(parent.end)
            width = Dimension.value(100.dp)
//                width = Dimension.fillToConstraints
            height = Dimension.value(100.dp)
        }

        createHorizontalChain(greenBox, redBox, chainStyle = ChainStyle.Packed)
    }

    ConstraintLayout(
        constraint,
        modifier = Modifier.fillMaxSize()
    ) {

        Box(
            modifier = Modifier
                .background(color = Color.Green)
                .layoutId("greenBox")
        )

        Box(
            modifier = Modifier
                .background(color = Color.Red)
                .layoutId("redBox")
        )

    }

}

@Preview(showSystemUi = true)
@Composable
fun RecyclerViewAsLazyColumn() {
    //Lazy Column == Recycler View

    LazyColumn(content = {
        itemsIndexed(
            listOf("This", "is", "Jetpack", "Compose")
        ) { index, string ->
            Text(
                text = "Item $index, $string",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 24.dp)
            )
        }
        items(5000) {
//                Text(
//                    text = "Item $it",
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 24.sp,
//                    textAlign = TextAlign.Center,
//                    modifier = Modifier
//                        .fillMaxSize()
//                        .padding(vertical = 24.dp)
//                )
        }
    })


}


@Composable
fun TextFieldsButtonsAndSnackbar() {

    val scaffoldState = rememberScaffoldState()
    var textFieldState by remember {
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
                .padding(it)
        ) {
            OutlinedTextField(
                value = textFieldState,
                label = {
                    Text(text = "Enter Your Name")
                },
                onValueChange = {
                    textFieldState = it
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedButton(onClick = {
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(
                        "Hello, $textFieldState"
                    )
                }
            }) {
                Text(text = "Show Snackbar")
            }
        }
    }

}

@Composable
fun StylingText() {

    val fontFamily = FontFamily(
        Font(R.font.lexend_thin, FontWeight.Thin),
        Font(R.font.lexend_light, FontWeight.Light),
        Font(R.font.lexend_regular, FontWeight.Normal),
        Font(R.font.lexend_medium, FontWeight.Medium),
        Font(R.font.lexend_semibold, FontWeight.SemiBold),
        Font(R.font.lexend_bold, FontWeight.Bold),
        Font(R.font.lexend_extrabold, FontWeight.ExtraBold)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF101010))
    ) {

        Text(
//                text = "Jetpack Compose",
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = Color.Green,
                        fontSize = 50.sp,
                    )
                ) {
                    append("J")
                }
                append("etpack ")

                withStyle(
                    style = SpanStyle(
                        color = Color.Green,
                        fontSize = 50.sp,
                    )
                ) {
                    append("C")
                }
                append("ompose")
            },
            color = Color(0xFFFFFFFF),
            fontSize = 30.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline
        )

    }

}

@Composable
fun ShowPreviewState() {

    Column(modifier = Modifier.fillMaxSize()) {

        val color = remember {
            mutableStateOf(Color.Yellow)
        }

        ColorBox(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
        ) {
            color.value = it
        }

        Box(
            modifier = Modifier
                .background(color.value)
                .weight(1f)
                .fillMaxSize()
        )
    }

}


@Composable
fun ColorBox(
    modifier: Modifier = Modifier,
    updateColor: (Color) -> Unit
) {
    //State in Jetpack Compose

    Box(modifier = modifier
        .background(Color.Red)
        .clickable {
            updateColor(
                Color(
                    Random.nextFloat(),
                    Random.nextFloat(),
                    Random.nextFloat(),
                    1f
                )
            )
        })

}

@Composable
fun ImageCard(
    painter: Painter,
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(15.dp),
        elevation = 5.dp
    ) {

        Box(modifier = Modifier.fillMaxSize(0.5f)) {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 300f
                        )
                    )
            ) {

            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {

                Text(
                    text = title,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 16.sp
                    )
                )

            }
        }

    }
}

@Composable
fun Card() {

    Row(
        modifier = Modifier
            .width(IntrinsicSize.Max)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(0.5f)
                .weight(weight = 0.5f, fill = true)
                .padding(16.dp)
        ) {
            ImageCard(
                painter = painterResource(id = R.drawable.aviral_kaushik),
                contentDescription = "Aviral Kaushik",
                title = "Aviral Kaushik Android Developer"
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize(0.5f)
                .weight(weight = 0.5f, fill = true)
                .padding(16.dp)
        ) {
            ImageCard(
                painter = painterResource(id = R.drawable.avichal_kaushik),
                contentDescription = "Avichal Kaushik",
                title = "Avichal Kaushik Full Stack Developer"
            )
        }
    }


}

@Composable
fun Modifiers() {

    Column(
        modifier = Modifier
            .background(Color.Green)
            .fillMaxHeight(0.5f)
            .fillMaxWidth()
            .border(5.dp, Color.Magenta)
            .padding(16.dp)
            .border(5.dp, Color.Red)
            .padding(5.dp)
            .border(5.dp, Color.Blue)
//                .padding(top = 15.dp, bottom = 15.dp, start = 15.dp, end = 15.dp)
//                .requiredWidth(150.dp)
    ) {
        Text(
            text = "Hello",
//                modifier = Modifier
//                    .offset(50.dp, 20.dp)
        )
        Spacer(
            modifier = Modifier
                .height(20.dp)
        )
        Text(text = "World", modifier = Modifier.clickable {

        })
    }

}


@Composable
fun RowsAndColumns() {
    Column(
        modifier = Modifier
            .background(Color.Green),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(0.5f)
                .background(Color.Red),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Hello")
            Text(text = "World")
        }

        Row(
            modifier = Modifier
                .fillMaxSize(0.5f)
                .background(Color.Red),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Hello")
            Text(text = "Aviral")
        }
    }
}

@Composable
fun Navigation(navController: NavHostController) {
    val navHost = NavHost(navController = navController, startDestination = "home") {
        composable(route = "home") {
            HomeScreen()
        }
        composable(route = "chat") {
            ChatScreen()
        }
        composable(route = "settings") {
            SettingsScreen()
        }
    }
}

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {

    val backStackEntry = navController.currentBackStackEntryAsState()

    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color.DarkGray,
        elevation = 5.dp
    ) {

        items.forEach { item ->

            val selected = item.route == backStackEntry.value?.destination?.route

            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(item) },
                selectedContentColor = Color.Green,
                unselectedContentColor = Color.Gray,
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        if (item.badgeCount > 0) {
                            BadgedBox(badge = {
                                Text(text = item.badgeCount.toString())
                            }) {
                                Icon(imageVector = item.icon, contentDescription = item.name)
                            }
                        } else {
                            Icon(imageVector = item.icon, contentDescription = item.name)
                        }
                        if (selected) {
                            Text(text = item.name, textAlign = TextAlign.Center, fontSize = 10.sp)
                        }
                    }
                }
            )
        }

    }

}

@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Text(text = "Home Screen")

    }
}

@Composable
fun ChatScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Text(text = "Chat Screen")

    }
}

@Composable
fun SettingsScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Text(text = "Settings Screen")

    }
}