package yampi.msh.abohava.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import yampi.msh.abohava.domain.model.ForecastDayModel
import yampi.msh.abohava.domain.model.ForecastTemperatureModel
import yampi.msh.abohava.util.Blue500
import yampi.msh.abohava.util.Blue800
import yampi.msh.abohava.util.getConditionBackground
import yampi.msh.abohava.util.getConditionIcon

private const val animationDurationMillis = 300
private const val titleFontScaleStart = 1f
private const val titleFontScaleEnd = 0.66f

private val xSmallSize = 4.dp
private val smallSize = 8.dp
private val mediumSize = 16.dp
private val xMediumSize = 24.dp
private val largeSize = 32.dp
private val xLargeSize = 40.dp

private val mediumFontSize = 18.sp
private val largeFontSize = 32.sp
private val xLargeFontSize = 48.sp
private val xxLargeFontSize = 96.sp

private val weatherIconSize = 100.dp

private val headerHeight = 320.dp
private val toolbarHeight = 56.dp

private val paddingMedium = 48.dp

private val titlePaddingEnd = 72.dp

private val blurRadius = 10.dp

@Composable
fun CollapsingToolbarParallaxEffect(
    temperatureState: ForecastTemperatureModel,
    modifier: Modifier = Modifier,
    forecastDay: ForecastDayModel?,
    isDay: Boolean,
) {
    val scroll: ScrollState = rememberScrollState(0)

    val headerHeightPx = with(LocalDensity.current) { headerHeight.toPx() }
    val toolbarHeightPx = with(LocalDensity.current) { toolbarHeight.toPx() }

    Box(modifier = modifier) {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .blur(radius = blurRadius),
            model = getConditionBackground(temperatureState.currentModel?.conditionModel?.code ?: 0, isDay),
            contentScale = ContentScale.Crop,
            contentDescription = ""
        )
        Header(
            scroll = scroll,
            headerHeightPx = headerHeightPx,
            modifier = Modifier.fillMaxWidth(),
            headerContent = {
                Box(modifier = Modifier.wrapContentSize()) {
                    Column(
                        modifier = Modifier
                            .padding()
                            .fillMaxWidth(fraction = 1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(weatherIconSize), Alignment.Center
                        ) {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(getConditionIcon(temperatureState.currentModel?.conditionModel?.code ?: 0, isDay))
                                    .decoderFactory(SvgDecoder.Factory())
                                    .build(),
                                contentDescription = "Translated description of what the image contains"
                            )
                        }
                        Text(
                            text = "${temperatureState.currentModel?.tempC}",
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.W300,
                            fontSize = xxLargeFontSize,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.size(width = 0.dp, height = xLargeSize))
                        Text(
                            text = "${temperatureState.currentModel?.conditionModel?.text}",
                            textAlign = TextAlign.Center,
                            fontSize = mediumFontSize,
                            fontWeight = FontWeight.Light,
                            color = Color.White
                        )
                        Row {
                            Text(
                                text = "H: ${forecastDay?.day?.maxtempC}",
                                textAlign = TextAlign.Center,
                                fontSize = mediumFontSize,
                                fontWeight = FontWeight.Light,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.size(mediumSize, 0.dp))
                            Text(
                                text = "L: ${forecastDay?.day?.mintempC}",
                                textAlign = TextAlign.Center,
                                fontSize = mediumFontSize,
                                fontWeight = FontWeight.Light,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        )
        Body(
            temperatureState = temperatureState,
            scroll = scroll,
            modifier = Modifier.fillMaxSize(),
            forecastDay = forecastDay,
            isDay = isDay,
        )
        Toolbar(
            scroll = scroll,
            headerHeightPx = headerHeightPx,
            toolbarHeightPx = toolbarHeightPx
        )
        Title(scroll = scroll, text = "${temperatureState.locationModel?.name}")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Toolbar(
    scroll: ScrollState,
    headerHeightPx: Float,
    toolbarHeightPx: Float,
    modifier: Modifier = Modifier
) {
    val toolbarBottom by remember {
        mutableStateOf(headerHeightPx - toolbarHeightPx)
    }

    val showToolbar by remember {
        derivedStateOf {
            scroll.value >= toolbarBottom
        }
    }

    AnimatedVisibility(
        modifier = modifier,
        visible = showToolbar,
        enter = fadeIn(animationSpec = tween(animationDurationMillis)),
        exit = fadeOut(animationSpec = tween(animationDurationMillis))
    ) {
        TopAppBar(
            modifier = Modifier.background(
                brush = Brush.horizontalGradient(
                    listOf(Blue500, Blue800)
                )
            ),
            navigationIcon = {
                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .padding(mediumSize)
                        .size(xMediumSize)
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            },
            title = {},
        )
    }
}

@Composable
private fun Header(
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
private fun Body(
    temperatureState: ForecastTemperatureModel,
    scroll: ScrollState,
    modifier: Modifier = Modifier,
    forecastDay: ForecastDayModel?,
    isDay: Boolean,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.verticalScroll(scroll)
    ) {
        Spacer(modifier = Modifier.size(width = 0.dp, height = headerHeight))
        ForecastDayListView(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(mediumSize),
            title = "HOURLY FORECAST",
            hours = forecastDay?.hour,
            isDay = isDay,
            iconUrl = "https://www.svgrepo.com/show/427039/weather-icons-67.svg"
        )
        ForecastDaysView(
            title = "10-DAY FORECAST",
            forecasts = temperatureState.forecastModel?.forecastday,
            isDay = isDay,
            iconUrl = "https://www.svgrepo.com/show/423032/calendar-weather-cloud.svg"
        )
        Spacer(modifier = Modifier.size(width = 0.dp, height = mediumSize))
        DayTemperatureDetails(
            temperatureState = temperatureState,
            forecastDay = forecastDay,
            isDay = isDay
        )
        Spacer(modifier = Modifier.size(width = 0.dp, height = xMediumSize))
    }
}

@Composable
fun Title(
    scroll: ScrollState,
    modifier: Modifier = Modifier.wrapContentSize(),
    text: String
) {
    val centerOfScreen = LocalConfiguration.current.screenWidthDp / 2f
    var titleHeightPx by remember { mutableStateOf(0f) }
    var titleWidthPx by remember { mutableStateOf(0f) }
    var startOfHorizontalCenterTextAlign by remember { mutableStateOf(0f) }
    Text(
        text = text,
        fontSize = largeFontSize,
        textAlign = TextAlign.Center,
        color = Color.White,
        modifier = modifier
            .graphicsLayer {
                val collapseRange: Float = (headerHeight.toPx() - toolbarHeight.toPx())
                val collapseFraction: Float = (scroll.value / collapseRange).coerceIn(0f, 1f)

                val scaleXY = lerp(
                    titleFontScaleStart.dp,
                    titleFontScaleEnd.dp,
                    collapseFraction
                )

                val titleExtraStartPadding = titleWidthPx.toDp() * (1 - scaleXY.value) / 2f
                startOfHorizontalCenterTextAlign = centerOfScreen - titleWidthPx / 8

                val titleYFirstInterpolatedPoint = lerp(
                    headerHeight - titleHeightPx.toDp() - paddingMedium,
                    headerHeight / 2,
                    collapseFraction
                )

                val titleXFirstInterpolatedPoint = lerp(
                    startOfHorizontalCenterTextAlign.dp,
                    (titlePaddingEnd - titleExtraStartPadding) * 5 / 4,
                    collapseFraction
                )

                val titleYSecondInterpolatedPoint = lerp(
                    10.dp / 2,
                    toolbarHeight / 2 - titleHeightPx.toDp() / 2,
                    collapseFraction
                )

                val titleXSecondInterpolatedPoint = lerp(
                    (titlePaddingEnd - titleExtraStartPadding) * 5 / 4,
                    titlePaddingEnd - titleExtraStartPadding,
                    collapseFraction
                )

                val titleY = lerp(
                    titleYFirstInterpolatedPoint,
                    titleYSecondInterpolatedPoint,
                    collapseFraction
                )

                val titleX = lerp(
                    titleXFirstInterpolatedPoint,
                    titleXSecondInterpolatedPoint,
                    collapseFraction
                )

                translationY = titleY.toPx()
                translationX = titleX.toPx()
                scaleX = scaleXY.value
                scaleY = scaleXY.value
            }
            .onGloballyPositioned {
                titleHeightPx = it.size.height.toFloat()
                titleWidthPx = it.size.width.toFloat()
            }
    )
}