package yampi.msh.abohava.util

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColorScheme(
    primary = primary,
    primaryContainer = primaryVariant,
    secondary = secondary,
    background = background,
    surface = surface,
)

@Composable
fun CollapsingToolbarTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = DarkColorPalette,
        content = content
    )
}