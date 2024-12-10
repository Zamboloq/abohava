package yampi.msh.abohava.ui.home

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun HeaderView(
    scroll: ScrollState,
    headerHeightPx: Float,
    modifier: Modifier = Modifier,
    headerContent: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .graphicsLayer {
                translationY = -scroll.value.toFloat() / 2f
                alpha = (-1f / headerHeightPx) * scroll.value + 1
            }
    ) {
        headerContent()
    }
}

@Composable
fun LeftBox(
    modifier: Modifier = Modifier,
    headerContent: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
    ) {
        headerContent()
    }
}