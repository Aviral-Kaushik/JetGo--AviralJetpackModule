package com.aviral.jetpackbasics.navigrationDrawer

import androidx.compose.ui.graphics.vector.ImageVector

data class NavDrawerMenuItem(
    val id: String,
    val title: String,
    val contentDescription: String,
    val icon: ImageVector
)
