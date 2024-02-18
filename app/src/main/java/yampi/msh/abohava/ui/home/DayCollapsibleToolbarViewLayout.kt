package yampi.msh.abohava.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import yampi.msh.abohava.domain.model.ForecastDayModel
import yampi.msh.abohava.domain.model.ForecastTemperatureModel
import yampi.msh.abohava.ui.CollapsingToolbarParallaxEffect
import yampi.msh.abohava.util.CollapsingToolbarTheme
import yampi.msh.abohava.util.surface

@Composable
fun DayCollapsibleToolbarViewLayout(state: MutableState<ForecastTemperatureModel>) {
    var temperatureState: ForecastTemperatureModel by remember { state }

    val forecastDay = temperatureState.forecastModel?.forecastday?.let {
        val forecastDay = if (it.isNullOrEmpty()) {
            ForecastDayModel()
        } else it.first()
        forecastDay
    }

    val isDay = temperatureState.currentModel?.isDay == 1

    CollapsingToolbarTheme {
        Surface {
            CollapsingToolbarParallaxEffect(
                temperatureState = temperatureState,
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = surface),
                forecastDay = forecastDay,
                isDay = isDay
            )
        }
    }
}