package dme.systems.brainbobapp

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import dme.systems.brainbobapp.ui.theme.Seal

data class RecommendedItem(
    val title: String,
    val listenMinutes: Int,
    val icon: ImageVector,
    val bookmarked: Boolean,
    val color: Color = Color.Red
)
