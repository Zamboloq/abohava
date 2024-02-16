package yampi.msh.abohava.ui

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import yampi.msh.abohava.domain.model.ForecastDayModel
import yampi.msh.abohava.domain.model.ForecastTemperatureModel
import yampi.msh.abohava.util.getConditionIcon
import yampi.msh.abohava.util.setBackgroundCardColor
import yampi.msh.abohava.util.setBackgroundColor
import yampi.msh.abohava.util.setBackgroundDividerColor
import java.time.LocalDate
import java.time.format.TextStyle

@Composable
fun dayViewLayout(state: MutableState<ForecastTemperatureModel>) {
    var temperatureState: ForecastTemperatureModel by remember { state }

    val forecastDay = temperatureState.forecastModel?.forecastday?.let {
        val forecastDay = if (it.isNullOrEmpty()) {
            ForecastDayModel()
        } else it.first()
        forecastDay
    }

    val isDay = temperatureState.currentModel?.isDay == 1

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(setBackgroundColor(isDay))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {

            Spacer(modifier = Modifier.size(width = 0.dp, height = 24.dp))

            MainTemperatureView(temperatureState = temperatureState, forecastDay = forecastDay, isDay = isDay)

            Spacer(modifier = Modifier.size(width = 0.dp, height = 24.dp))

            ForecastDayListView(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp),
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

            Spacer(modifier = Modifier.size(width = 0.dp, height = 16.dp))

            DayTemperatureDetails(temperatureState = temperatureState, forecastDay = forecastDay, isDay = isDay)

            Spacer(modifier = Modifier.size(width = 0.dp, height = 24.dp))
        }
    }
}

@Composable
fun ForecastDaysView(title: String, forecasts: ArrayList<ForecastDayModel>?, isDay: Boolean, iconUrl: String = "") {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(16.dp),
        elevation = CardDefaults.outlinedCardElevation(),
        border = CardDefaults.outlinedCardBorder(),
        colors = setBackgroundCardColor(isDay),
    ) {
        if (forecasts.isNullOrEmpty()) {
            //TODO SHOW A MESSAGE ABOUT WE HAVE NO ANY FORECAST INFORMATION FOR NEXT WEEK
        } else {
            Column(modifier = Modifier.padding(8.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(32.dp, 32.dp), Alignment.Center
                    ) {
                        AsyncImage(
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(iconUrl)
                                .decoderFactory(SvgDecoder.Factory())
                                .build(),
                            contentDescription = "Translated description of what the image contains"
                        )
                    }
                    Text(
                        text = title,
                        textAlign = TextAlign.Start,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Light,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.size(0.dp, 4.dp))
                LazyColumn {
                    items(forecasts) { forecast ->
                        val day = forecast.day
                        val dayName = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            LocalDate.parse(forecast.date).dayOfWeek.getDisplayName(
                                TextStyle.SHORT,
                                java.util.Locale.US
                            )
                        } else {
                            TODO("VERSION.SDK_INT < O")
                        }
                        Divider(
                            modifier = Modifier.padding(top = 8.dp, bottom = 8.dp),
                            color = setBackgroundDividerColor(isDay = isDay)
                        )
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .clickable {}) {
                            Column {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = "$dayName",
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.width(28.dp)
                                    )
//                                    Spacer(modifier = Modifier.size(16.dp, 0.dp))
                                    Box(
                                        modifier = Modifier
                                            .size(40.dp, 40.dp)
                                            .weight(1f), Alignment.Center
                                    ) {
                                        AsyncImage(
                                            model = ImageRequest.Builder(LocalContext.current)
                                                .data(getConditionIcon(day?.condition?.code ?: 0, true))
                                                .decoderFactory(SvgDecoder.Factory())
                                                .build(),
                                            contentDescription = "Translated description of what the image contains"
                                        )
                                    }
//                                    Spacer(modifier = Modifier.size(16.dp, 0.dp))
                                    Row(
                                        modifier = Modifier
                                            .wrapContentSize()
                                            .weight(1f),
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(20.dp, 20.dp), Alignment.Center
                                        ) {
                                            AsyncImage(
                                                model = ImageRequest.Builder(LocalContext.current)
                                                    .data("https://www.svgrepo.com/show/422009/climate-forecast-plus.svg")
                                                    .decoderFactory(SvgDecoder.Factory())
                                                    .build(),
                                                contentDescription = "Translated description of what the image contains"
                                            )
                                        }
                                        Text(
                                            text = "${day?.maxtempC}",
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.width(32.dp)
                                        )
                                        Box(
                                            modifier = Modifier
                                                .size(20.dp, 20.dp), Alignment.Center
                                        ) {
                                            AsyncImage(
                                                model = ImageRequest.Builder(LocalContext.current)
                                                    .data("https://www.svgrepo.com/show/422017/climate-forecast-minus.svg")
                                                    .decoderFactory(SvgDecoder.Factory())
                                                    .build(),
                                                contentDescription = "Translated description of what the image contains"
                                            )
                                        }
                                        Text(
                                            text = "${day?.mintempC}",
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.width(32.dp)
                                        )
                                    }
//                                    Spacer(modifier = Modifier.size(16.dp, 0.dp))
                                    Row(
                                        modifier = Modifier
                                            .wrapContentSize()
                                            .weight(1f)
                                    ) {
                                        Column {
                                            Box(
                                                modifier = Modifier
                                                    .size(20.dp, 20.dp), Alignment.Center
                                            ) {
                                                AsyncImage(
                                                    model = ImageRequest.Builder(LocalContext.current)
                                                        .data("https://www.svgrepo.com/show/422024/climate-cloud-forecast-2.svg")
                                                        .decoderFactory(SvgDecoder.Factory())
                                                        .build(),
                                                    contentDescription = "Translated description of what the image contains"
                                                )
                                            }
                                            Text(text = "${day?.dailyChanceOfRain}%", fontSize = 12.sp)
                                        }
                                        Spacer(modifier = Modifier.size(16.dp, 0.dp))
                                        Column {
                                            Box(
                                                modifier = Modifier
                                                    .size(20.dp, 20.dp), Alignment.Center
                                            ) {
                                                AsyncImage(
                                                    model = ImageRequest.Builder(LocalContext.current)
                                                        .data("https://www.svgrepo.com/show/422006/forecast-snowflake-flakes.svg")
                                                        .decoderFactory(SvgDecoder.Factory())
                                                        .build(),
                                                    contentDescription = "Translated description of what the image contains"
                                                )
                                            }
                                            Text(text = "${day?.dailyChanceOfSnow}%", fontSize = 12.sp)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MainTemperatureView(temperatureState: ForecastTemperatureModel, forecastDay: ForecastDayModel?, isDay: Boolean) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp), Alignment.Center
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(getConditionIcon(temperatureState.currentModel?.conditionModel?.code ?: 0, isDay))
                .decoderFactory(SvgDecoder.Factory())
                .build(),
            contentDescription = "Translated description of what the image contains"
        )
    }
    Box(modifier = Modifier.wrapContentSize()) {
        Column(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(fraction = 1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${temperatureState.locationModel?.name}",
                textAlign = TextAlign.Center,
                fontSize = 32.sp,
                color = Color.White
            )
            Row {
                Text(
                    text = "${temperatureState.currentModel?.tempC}",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.W300,
                    fontSize = 96.sp,
                    color = Color.White
                )
//                        Box(
//                            modifier = Modifier
//                                .size(48.dp, 48.dp), Alignment.TopStart
//                        ) {
//                            AsyncImage(
//                                model = ImageRequest.Builder(LocalContext.current)
//                                    .data("https://bmcdn.nl/assets/weather-icons/v3.0/fill/svg/celsius.svg")
//                                    .decoderFactory(SvgDecoder.Factory())
//                                    .build(),
//                                contentDescription = "Translated description of what the image contains"
//                            )
//                        }
            }
            Text(
                text = "${temperatureState.currentModel?.conditionModel?.text}",
                textAlign = TextAlign.Center,
                fontSize = 18.sp,
                fontWeight = FontWeight.Light,
                color = Color.White
            )
            Row {
                Text(
                    text = "H: ${forecastDay?.day?.maxtempC}",
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.White
                )
                Spacer(modifier = Modifier.size(16.dp, 0.dp))
                Text(
                    text = "L: ${forecastDay?.day?.mintempC}",
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Light,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun DayTemperatureDetails(temperatureState: ForecastTemperatureModel, forecastDay: ForecastDayModel?, isDay: Boolean) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Spacer(modifier = Modifier.size(width = 16.dp, height = 0.dp))
        CardTemperatureFeatureView(
            modifier = Modifier.weight(1f),
            title = "UV INDEX",
            value = temperatureState.currentModel?.uv.toString(),
            symbol = "",
            message = "Low levels all day.",
            url = "https://bmcdn.nl/assets/weather-icons/v3.0/fill/svg/uv-index.svg",
            isDay = isDay
        )
        Spacer(modifier = Modifier.size(width = 16.dp, height = 0.dp))
        CardTemperatureFeatureView(
            modifier = Modifier.weight(1f),
            title = "SUNRISE",
            value = forecastDay?.astro?.sunrise.toString(),
            symbol = "",
            message = "The dew point is ${temperatureState.currentModel?.tempC}C right now.",
            url = "https://bmcdn.nl/assets/weather-icons/v3.0/fill/svg/time-morning.svg",
            isDay = isDay
        )
        Spacer(modifier = Modifier.size(width = 16.dp, height = 0.dp))
    }

    Spacer(modifier = Modifier.size(width = 0.dp, height = 24.dp))

    Row(verticalAlignment = Alignment.CenterVertically) {
        Spacer(modifier = Modifier.size(width = 16.dp, height = 0.dp))
        CardTemperatureFeatureView(
            modifier = Modifier.weight(1f),
            title = "FEELS LIKE",
            value = temperatureState.currentModel?.feelslikeC.toString(),
            symbol = "C",
            message = "Wind is making it feel colder.",
            url = "https://bmcdn.nl/assets/weather-icons/v3.0/fill/svg/thermometer-colder.svg",
            isDay = isDay
        )
        Spacer(modifier = Modifier.size(width = 16.dp, height = 0.dp))
        CardTemperatureFeatureView(
            modifier = Modifier.weight(1f),
            title = "HUMIDITY",
            value = temperatureState.currentModel?.humidity.toString(),
            symbol = "%",
            message = "The dew point is ${temperatureState.currentModel?.tempC}C right now.",
            url = "https://bmcdn.nl/assets/weather-icons/v3.0/fill/svg/humidity.svg",
            isDay = isDay
        )
        Spacer(modifier = Modifier.size(width = 16.dp, height = 0.dp))
    }

    Spacer(modifier = Modifier.size(width = 0.dp, height = 24.dp))

    Row(verticalAlignment = Alignment.CenterVertically) {
        Spacer(modifier = Modifier.size(width = 16.dp, height = 0.dp))
        CardTemperatureFeatureView(
            modifier = Modifier.weight(1f),
            title = "VISIBILITY",
            value = temperatureState.currentModel?.visKm.toString(),
            symbol = "km",
            message = "It's perfectly clear right now.",
            url = "https://bmcdn.nl/assets/weather-icons/v3.0/fill/svg/code-green.svg",
            isDay = isDay
        )
        Spacer(modifier = Modifier.size(width = 16.dp, height = 0.dp))
        CardTemperatureFeatureView(
            modifier = Modifier.weight(1f),
            title = "WIND",
            value = temperatureState.currentModel?.windKph.toString(),
            symbol = "km/h",
            message = "temperatureState.currentModel?.windDir",
            url = "https://bmcdn.nl/assets/weather-icons/v3.0/fill/svg/wind.svg",
            isDay = isDay
        )
        Spacer(modifier = Modifier.size(width = 16.dp, height = 0.dp))
    }
}

@Preview
@Composable
fun dayViewLayoutPreview() {
    var temperatureState = mutableStateOf(ForecastTemperatureModel())
    dayViewLayout(temperatureState)
}