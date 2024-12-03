package org.example.schwarz.it.lws.weatherforecast

import kotlinx.coroutines.runBlocking
import java.util.logging.Level
import java.util.logging.Logger

fun main() = runBlocking {
    val logger: Logger = Logger.getLogger("Main")
    logger.info("Wetter-App gestartet")

    println("Willkommen zur Wetter-App!")
    print("Bitte gib den Stadtnamen ein: ")
    val city = readlnOrNull()

    if (city.isNullOrBlank()) {
        logger.warning("Leerer Stadtnamen eingegeben")
        println("Stadtnamen darf nicht leer sein.")
        return@runBlocking
    }

    val apiKey = "<ENTER YOUR API KEY>"
    val weatherApp = WeatherApp(WeatherClient.weatherService, apiKey)

    try {
        logger.info("Abrufen der Wetterdaten für Stadt: $city")
        val (currentWeatherStr, forecastStr) = weatherApp.getAndDisplayWeather(city)
        println(currentWeatherStr)
        println(forecastStr)
        logger.info("Wetterdaten erfolgreich angezeigt für Stadt: $city")
    } catch (e: Exception) {
        logger.log(Level.SEVERE, "Fehler beim Abrufen oder Anzeigen der Wetterdaten für Stadt: $city", e)
        println("Fehler beim Abrufen der Wetterdaten: ${e.message}")
    }

    logger.info("Wetter-App beendet")
}
