package yampi.msh.abohava.ui.home

import ToolbarView
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import yampi.msh.abohava.domain.model.ConditionModel
import yampi.msh.abohava.domain.model.CurrentModel
import yampi.msh.abohava.domain.model.ForecastDayModel
import yampi.msh.abohava.domain.model.ForecastTemperatureModel
import yampi.msh.abohava.util.getConditionIcon
import yampi.msh.abohava.util.largeFontSize
import yampi.msh.abohava.util.mediumSize
import yampi.msh.abohava.util.xLargeSize
import yampi.msh.abohava.util.xxMediumFontSize
import yampi.msh.abohava.util.xxxLargeFontSize
import kotlin.math.roundToInt

private val weatherIconSize = 100.dp
private val headerHeight = 320.dp
private val maxWidthForPhone = 600.dp

@Composable
fun ContentDependencyLayout(
    scroll: ScrollState,
    headerHeightPx: Float,
    temperatureState: ForecastTemperatureModel,
    forecastDay: ForecastDayModel?,
    currentModel: CurrentModel?,
    conditionModel: ConditionModel?,
    toolbarHeightPx: Float,
    conditionModelCode: Int,
    isDay: Boolean? = true
) {
    val configuration = LocalConfiguration.current
    val maxWidth = configuration.screenWidthDp.dp

    if (maxWidth < maxWidthForPhone) {
        phoneLayout(
            scroll = scroll,
            headerHeightPx = headerHeightPx,
            toolbarHeightPx =  toolbarHeightPx,
            temperatureState = temperatureState,
            currentModel = currentModel,
            conditionModel = conditionModel,
            conditionModelCode = conditionModelCode,
            forecastDay = forecastDay,
            isDay = isDay)
    } else {
        tabletLayout(
            scroll = scroll,
            temperatureState = temperatureState,
            currentModel = currentModel,
            conditionModel = conditionModel,
            conditionModelCode = conditionModelCode,
            forecastDay = forecastDay,
            isDay = isDay)
    }
}

@Composable
fun phoneLayout(
    scroll: ScrollState,
    headerHeightPx: Float,
    temperatureState: ForecastTemperatureModel,
    forecastDay: ForecastDayModel?,
    currentModel: CurrentModel?,
    conditionModel: ConditionModel?,
    toolbarHeightPx: Float,
    conditionModelCode: Int,
    isDay: Boolean? = true
) {
    HeaderView(
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
                        isDay?.let {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(getConditionIcon(conditionModelCode, it))
                                    .decoderFactory(SvgDecoder.Factory())
                                    .build(),
                                contentDescription = "Translated description of what the image contains"
                            )
                        }
                    }
                    Text(
                        text = "${currentModel?.tempC?.roundToInt()}",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.W300,
                        fontSize = xxxLargeFontSize,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.size(width = 0.dp, height = xLargeSize))
                    Text(
                        text = "${conditionModel?.text}",
                        textAlign = TextAlign.Center,
                        fontSize = xxMediumFontSize,
                        fontWeight = FontWeight.Light,
                        color = Color.White
                    )
                    Row {
                        Text(
                            text = "H: ${forecastDay?.day?.maxtempC?.roundToInt()}",
                            textAlign = TextAlign.Center,
                            fontSize = xxMediumFontSize,
                            fontWeight = FontWeight.Light,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.size(mediumSize, 0.dp))
                        Text(
                            text = "L: ${forecastDay?.day?.mintempC?.roundToInt()}",
                            textAlign = TextAlign.Center,
                            fontSize = xxMediumFontSize,
                            fontWeight = FontWeight.Light,
                            color = Color.White
                        )
                    }
                }
            }
        }
    )
    BodyView(
        temperatureState = temperatureState,
        scroll = scroll,
        modifier = Modifier.fillMaxSize(),
        forecastDay = forecastDay,
        topPadding = headerHeight,
        isDay = isDay,
    )
    ToolbarView(
        scroll = scroll,
        headerHeightPx = headerHeightPx,
        toolbarHeightPx = toolbarHeightPx
    )
    TitleView(scroll = scroll, text = "${temperatureState.locationModel?.name}")
}

@Composable
fun tabletLayout(
    scroll: ScrollState,
    temperatureState: ForecastTemperatureModel,
    currentModel: CurrentModel?,
    conditionModel: ConditionModel?,
    conditionModelCode: Int,
    forecastDay: ForecastDayModel?,
    isDay: Boolean? = true
) {
    Row (verticalAlignment = Alignment.CenterVertically) {
        LeftBox(
            modifier = Modifier.weight(.75f).align(Alignment.CenterVertically),
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
                            isDay?.let {
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(getConditionIcon(conditionModelCode, it))
                                        .decoderFactory(SvgDecoder.Factory())
                                        .build(),
                                    contentDescription = "Translated description of what the image contains"
                                )
                            }
                        }
                        Text(
                            text = "${currentModel?.tempC?.roundToInt()}",
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.W300,
                            fontSize = xxxLargeFontSize,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.size(width = 0.dp, height = xLargeSize))
                        Text(
                            text = "${temperatureState.locationModel?.name}",
                            fontSize = largeFontSize,
                            textAlign = TextAlign.Center,
                            color = Color.White,
                            modifier = Modifier.wrapContentWidth(align = Alignment.CenterHorizontally)
                        )

                        Spacer(modifier = Modifier.size(width = 0.dp, height = xLargeSize))
                        Text(
                            text = "${conditionModel?.text}",
                            textAlign = TextAlign.Center,
                            fontSize = xxMediumFontSize,
                            fontWeight = FontWeight.Light,
                            color = Color.White
                        )
                        Row {
                            Text(
                                text = "H: ${forecastDay?.day?.maxtempC?.roundToInt()}",
                                textAlign = TextAlign.Center,
                                fontSize = xxMediumFontSize,
                                fontWeight = FontWeight.Light,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.size(mediumSize, 0.dp))
                            Text(
                                text = "L: ${forecastDay?.day?.mintempC?.roundToInt()}",
                                textAlign = TextAlign.Center,
                                fontSize = xxMediumFontSize,
                                fontWeight = FontWeight.Light,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        )
        BodyView(
            temperatureState = temperatureState,
            scroll = scroll,
            modifier = Modifier.weight(1f),
            forecastDay = forecastDay,
            topPadding = 50.dp,
            isDay = isDay,
        )
    }
}