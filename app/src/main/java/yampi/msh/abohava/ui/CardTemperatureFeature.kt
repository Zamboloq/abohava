package yampi.msh.abohava.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import yampi.msh.abohava.domain.model.HourModel
import yampi.msh.abohava.util.getConditionIcon
import yampi.msh.abohava.util.largeSize
import yampi.msh.abohava.util.mediumFontSize
import yampi.msh.abohava.util.mediumSize
import yampi.msh.abohava.util.setBackgroundCardColor
import yampi.msh.abohava.util.xxMediumFontSize
import yampi.msh.abohava.util.xxxLargeSize

@Composable
fun ForecastDayListView(
    modifier: Modifier,
    title: String,
    hours: ArrayList<HourModel>?,
    isDay: Boolean = true,
    iconUrl: String
) {
    OutlinedCard(
        modifier = modifier,
        elevation = CardDefaults.outlinedCardElevation(),
        border = CardDefaults.outlinedCardBorder(),
        colors = setBackgroundCardColor(isDay),
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(largeSize, largeSize), Alignment.Center
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
                    fontSize = mediumFontSize,
                    fontWeight = FontWeight.Light,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.size(0.dp, 8.dp))
            LazyRow {
                hours?.let {
                    items(it) { hour ->
                        Column(
                            modifier = Modifier.padding(8.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = hour.time?.split(" ")?.get(1).orEmpty())
                            Box(
                                modifier = Modifier
                                    .size(40.dp, 40.dp), Alignment.Center
                            ) {
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(getConditionIcon(hour.condition?.code ?: 0, hour.isDay == 1))
                                        .decoderFactory(SvgDecoder.Factory())
                                        .build(),
                                    contentDescription = "Translated description of what the image contains"
                                )
                            }
                            Text(text = hour.tempC.toString())
//                            Text(text = hour.condition?.text.orEmpty())
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.size(0.dp, 8.dp))
        }
    }
}

@Composable
fun CardTemperatureFeatureView(
    modifier: Modifier,
    title: String,
    value: String,
    symbol: String,
    message: String,
    url: String,
    isDay: Boolean = true
) {
    OutlinedCard(
        modifier = modifier,
        elevation = CardDefaults.outlinedCardElevation(),
        border = CardDefaults.outlinedCardBorder(),
        colors = setBackgroundCardColor(isDay),
    ) {
        Column(
            modifier = Modifier
                .padding(mediumSize)
                .height(xxxLargeSize),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(largeSize, largeSize), Alignment.Center
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(url)
                            .decoderFactory(SvgDecoder.Factory())
                            .build(),
                        contentDescription = "Translated description of what the image contains"
                    )
                }
                Text(
                    text = title,
                    textAlign = TextAlign.Start,
                    fontSize = mediumFontSize,
                    fontWeight = FontWeight.Light,
                    color = Color.White
                )
            }
            Text(
                text = "$value $symbol",
                textAlign = TextAlign.Start,
                fontSize = xxMediumFontSize,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = message,
                textAlign = TextAlign.Start,
                fontSize = mediumFontSize,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}