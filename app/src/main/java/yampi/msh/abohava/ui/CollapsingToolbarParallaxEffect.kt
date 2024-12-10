package yampi.msh.abohava.ui

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import yampi.msh.abohava.domain.model.ForecastDayModel
import yampi.msh.abohava.domain.model.ForecastTemperatureModel
import yampi.msh.abohava.ui.home.ContentDependencyLayout
import yampi.msh.abohava.util.getConditionBackground
import yampi.msh.abohava.util.orZero

private val headerHeight = 320.dp
private val toolbarHeight = 56.dp

private val blurRadius = 10.dp

@Composable
fun CollapsingToolbarParallaxEffect(
    temperatureState: ForecastTemperatureModel,
    modifier: Modifier = Modifier,
    forecastDay: ForecastDayModel?,
    isDay: Boolean?,
) {
    val scroll: ScrollState = rememberScrollState(0)

    val headerHeightPx = with(LocalDensity.current) { headerHeight.toPx() }
    val toolbarHeightPx = with(LocalDensity.current) { toolbarHeight.toPx() }

    val currentModel = temperatureState.currentModel
    val conditionModel = currentModel?.conditionModel
    val conditionModelCode = conditionModel?.code.orZero()

    Box(modifier = modifier) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .blur(radius = blurRadius),
            model = getConditionBackground(conditionModelCode, isDay),
            contentScale = ContentScale.Crop,
            contentDescription = ""
        )
        ContentDependencyLayout(
            scroll = scroll,
            headerHeightPx = headerHeightPx,
            toolbarHeightPx =  toolbarHeightPx,
            temperatureState = temperatureState,
            currentModel = currentModel,
            conditionModel = conditionModel,
            conditionModelCode = conditionModelCode,
            forecastDay = forecastDay,
            isDay = isDay
        )
    }
}