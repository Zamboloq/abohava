package yampi.msh.abohava.ui.home

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import yampi.msh.abohava.domain.model.ForecastDayModel
import yampi.msh.abohava.domain.model.ForecastTemperatureModel
import yampi.msh.abohava.ui.DayTemperatureDetails
import yampi.msh.abohava.ui.ForecastDayListView
import yampi.msh.abohava.ui.ForecastDaysView
import yampi.msh.abohava.util.mediumSize
import yampi.msh.abohava.util.xxxLargeSize

private val defaultTopPadding = 10.dp

@Composable
fun BodyView(
    temperatureState: ForecastTemperatureModel,
    scroll: ScrollState,
    modifier: Modifier = Modifier,
    topPadding: Dp = defaultTopPadding,
    forecastDay: ForecastDayModel?,
    isDay: Boolean?,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.verticalScroll(scroll)
    ) {
        Spacer(modifier = Modifier.size(width = 0.dp, height = topPadding))
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
        Spacer(modifier = Modifier.size(width = 0.dp, height = xxxLargeSize))
    }
}