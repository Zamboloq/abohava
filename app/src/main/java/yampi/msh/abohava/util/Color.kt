package yampi.msh.abohava.util

import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val primary = Color(0, 191, 238)
val primaryVariant = Color(16, 78, 139)
val secondary = Color(79, 79, 79)
val secondaryVariant = Color(179, 179, 179)
val background = Color(79, 79, 79, 100)
val surface = Color(179, 179, 179, 200)
val Blue500 = Color(0xff026586)
val Blue800 = Color(0xff032C45)

fun setBackgroundColor(isDay: Boolean): Brush {
    return if (isDay) {
        Brush.verticalGradient(listOf(primary, primaryVariant))
    } else {
        Brush.verticalGradient(listOf(secondary, secondaryVariant))
    }

}

fun setBackgroundColorWithAlpha(): Brush {
    return Brush.verticalGradient(
        listOf(
            Color(79, 79, 79, 100),
            Color(179, 179, 179, 200)
        )
    )
}

@Composable
fun setBackgroundCardColor(isDay: Boolean): CardColors {
    return if (isDay) {
        CardDefaults.cardColors(
            containerColor = Color(105, 139, 105, 50),
            contentColor = Color.White
        )
    } else {
        CardDefaults.cardColors(
            containerColor = Color(119, 136, 153, 100),
            contentColor = Color.White
        )
    }
}

@Composable
fun setBackgroundDividerColor(isDay: Boolean): Color {
    return if (isDay) {
        Color(105, 139, 105, 200)
    } else {
        Color(119, 136, 153, 200)
    }
}