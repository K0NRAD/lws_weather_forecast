package org.example.schwarz.it.lws.weatherforecast

import java.util.*

fun displayCurrentWeather(weather: WeatherResponse): String {
    return """
        Aktuelles Wetter in ${weather.name}:
        Beschreibung: ${weather.weather[0].description.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }}
        Temperatur: ${weather.main.temp}°C
        Gefühlt: ${weather.main.feels_like}°C
        Min Temperatur: ${weather.main.temp_min}°C
        Max Temperatur: ${weather.main.temp_max}°C
        Luftdruck: ${weather.main.pressure} hPa
        Luftfeuchtigkeit: ${weather.main.humidity}%
    """.trimIndent()
}

fun displayForecast(forecast: ForecastResponse): String {
    val sb = StringBuilder()
    sb.appendLine("Fünf-Tage-Vorhersage für ${forecast.city.name}:")

    val dailyForecast = forecast.list.groupBy {
        it.dt_txt.substring(0, 10)
    }

    for ((date, items) in dailyForecast) {
        val (year, month, day) = date.split("-")
        sb.appendLine("\nDatum: $day.$month.$year")
        val minTemperature = items.minOf { it.main.temp_min }
        val maxTemperature = items.maxOf { it.main.temp_max }
        sb.appendLine("Min Temperatur: $minTemperature")
        sb.appendLine("Max Temperatur: $maxTemperature")
    }

    return sb.toString()
}
