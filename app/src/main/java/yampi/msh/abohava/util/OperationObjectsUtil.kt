package yampi.msh.abohava.util

//temperatureState.currentModel?.isDay
fun Int.intToBoolean() = this == 1

fun Int?.orZero() = this?: 0