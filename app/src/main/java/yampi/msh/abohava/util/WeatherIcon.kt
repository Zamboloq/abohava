package yampi.msh.abohava.util

import android.util.Log

fun getConditionIcon(condition: Int, isDay: Boolean = true): String {
    return when (condition) {
        1000 -> {
            //Sunny or Night Clear
            if (isDay) {
                "https://bmcdn.nl/assets/weather-icons/v3.0/fill/svg/clear-day.svg"
            } else {
                "https://bmcdn.nl/assets/weather-icons/v3.0/fill/svg/clear-night.svg"
            }
        }

        1003 -> {
            //Partly Cloudy
            if (isDay) {
                "https://bmcdn.nl/assets/weather-icons/v3.0/fill/svg/partly-cloudy-day.svg"
            } else {
                "https://bmcdn.nl/assets/weather-icons/v3.0/fill/svg/partly-cloudy-night.svg"
            }
        }

        1006 -> {
            //Cloudy
            if (isDay) {
                "https://bmcdn.nl/assets/weather-icons/v3.0/fill/svg/cloudy.svg"
            } else {
                "https://bmcdn.nl/assets/weather-icons/v3.0/fill/svg/partly-cloudy-night.svg"
            }
        }

        1009 -> {
            //Overcast
            if (isDay) {
                "https://bmcdn.nl/assets/weather-icons/v3.0/fill/svg/overcast.svg"
            } else {
                "https://bmcdn.nl/assets/weather-icons/v3.0/fill/svg/overcast-night.svg"
            }
        }

        1063, 1180, 1183, 1186, 1189, 1192, 1195, 1198, 1201, 1240, 1243, 1246, 1273, 1276 -> {
            //Rain
            if (isDay) {
                "https://bmcdn.nl/assets/weather-icons/v3.0/fill/svg/rain.svg"
            } else {
                "https://bmcdn.nl/assets/weather-icons/v3.0/fill/svg/partly-cloudy-night-rain.svg"
            }
        }

        1066, 1072, 1114, 1117, 1210, 1213, 1216, 1219, 1222, 1225, 1255, 1258, 1279, 1282 -> {
            //Snow
            if (isDay) {
                "https://bmcdn.nl/assets/weather-icons/v3.0/fill/svg/snow.svg"
            } else {
                "https://bmcdn.nl/assets/weather-icons/v3.0/fill/svg/partly-cloudy-night-snow.svg"
            }
        }

        1069, 1204, 1207, 1249, 1252 -> {
            //Sleet
            if (isDay) {
                "https://bmcdn.nl/assets/weather-icons/v3.0/fill/svg/overcast-sleet.svg"
            } else {
                "https://bmcdn.nl/assets/weather-icons/v3.0/fill/svg/partly-cloudy-night-sleet.svg"
            }
        }

        1135, 1147 -> {
            //Fog
            if (isDay) {
                "https://bmcdn.nl/assets/weather-icons/v3.0/fill/svg/overcast-day-fog.svg"
            } else {
                "https://bmcdn.nl/assets/weather-icons/v3.0/fill/svg/partly-cloudy-night-fog.svg"
            }
        }

        1030 -> {
            //Mist
            if (isDay) {
                "https://bmcdn.nl/assets/weather-icons/v3.0/fill/svg/mist.svg"
            } else {
                "https://bmcdn.nl/assets/weather-icons/v3.0/fill/svg/mist.svg"
            }
        }

        else -> {
            Log.d("getConditionIcon", "else ? $condition")
            "https://bmcdn.nl/assets/weather-icons/v3.0/fill/svg/rainbow.svg"
        }
    }
}