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

fun getConditionBackground(condition: Int, isDay: Boolean = true): String {
    return when (condition) {
        1000 -> {
            //Sunny or Night Clear
            if (isDay) {
                "https://images.pexels.com/photos/912364/pexels-photo-912364.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"
            } else {
                "https://images.pexels.com/photos/1906658/pexels-photo-1906658.jpeg?cs=srgb&dl=pexels-egil-sj%C3%B8holt-1906658.jpg&fm=jpg"
            }
        }

        1003 -> {
            //Partly Cloudy
            if (isDay) {
                "https://images.pexels.com/photos/2931915/pexels-photo-2931915.jpeg?auto=compress&cs=tinysrgb&w=800"
            } else {
                "https://w.forfun.com/fetch/9e/9eb63026f9d5aa4bda09227118fee6c1.jpeg?h=1200&r=0.5"
            }
        }

        1006 -> {
            //Cloudy
            if (isDay) {
                "https://images.pexels.com/photos/531972/pexels-photo-531972.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"
            } else {
                "https://images.pexels.com/photos/3888585/pexels-photo-3888585.jpeg?auto=compress&cs=tinysrgb&w=800"
            }
        }

        1009 -> {
            //Overcast
            if (isDay) {
                "https://images.pexels.com/photos/1828305/pexels-photo-1828305.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"
            } else {
                "https://images.pexels.com/photos/3888585/pexels-photo-3888585.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"
            }
        }

        1063, 1180, 1183, 1186, 1189, 1192, 1195, 1198, 1201, 1240, 1243, 1246, 1273, 1276 -> {
            //Rain
            if (isDay) {
                "https://images.pexels.com/photos/125510/pexels-photo-125510.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"
            } else {
                "https://images.pexels.com/photos/2633046/pexels-photo-2633046.jpeg?auto=compress&cs=tinysrgb&w=800"
            }
        }

        1066, 1072, 1114, 1117, 1210, 1213, 1216, 1219, 1222, 1225, 1255, 1258, 1279, 1282 -> {
            //Snow
            if (isDay) {
                "https://images.pexels.com/photos/3563476/pexels-photo-3563476.jpeg?auto=compress&cs=tinysrgb&w=800"
            } else {
                "https://wallpapercave.com/wp/wp10362987.jpg"
            }
        }

        1069, 1204, 1207, 1249, 1252 -> {
            //Sleet
            if (isDay) {
                "https://wallpapers.com/images/hd/sleet-flakes-on-a-glass-hbpbx3dlg7yfpj4o.jpg"
            } else {
                "https://wallpapercave.com/wp/wp3335677.jpg"
            }
        }

        1135, 1147 -> {
            //Fog
            if (isDay) {
                "https://images.pexels.com/photos/325185/pexels-photo-325185.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2"
            } else {
                "https://images.pexels.com/photos/3772353/pexels-photo-3772353.jpeg?auto=compress&cs=tinysrgb&w=800"
            }
        }

        1030 -> {
            //Mist
            if (isDay) {
                "https://images.pexels.com/photos/4947176/pexels-photo-4947176.jpeg?auto=compress&cs=tinysrgb&w=800"
            } else {
                "https://images.pexels.com/photos/845619/pexels-photo-845619.jpeg?auto=compress&cs=tinysrgb&w=800"
            }
        }

        else -> {
            Log.d("getConditionIcon", "else ? $condition")
            "https://bmcdn.nl/assets/weather-icons/v3.0/fill/svg/rainbow.svg"
        }
    }
}