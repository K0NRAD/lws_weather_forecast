package org.example.schwarz.it.lws.weatherforecast

import java.util.logging.Level
import java.util.logging.Logger

class WeatherApp(
    private val weatherService: WeatherService,
    private val apiKey: String
) {
    private val logger: Logger = Logger.getLogger(WeatherApp::class.java.name)

    suspend fun getAndDisplayWeather(city: String): Pair<String, String> {
        logger.info("Start des Abrufs der Wetterdaten für Stadt: $city")
        try {
            val currentWeather = weatherService.getCurrentWeather(city, apiKey)
            logger.fine("Aktuelle Wetterdaten abgerufen: $currentWeather")

            val forecast = weatherService.getFiveDayForecast(city, apiKey)
            logger.fine("Fünf-Tage-Vorhersage abgerufen: $forecast")

            val currentWeatherStr = displayCurrentWeather(currentWeather)
            val forecastStr = displayForecast(forecast)

            logger.info("Erfolgreicher Abruf und Formatierung der Wetterdaten für Stadt: $city")
            return Pair(currentWeatherStr, forecastStr)
        } catch (e: Exception) {
            logger.log(Level.SEVERE, "Fehler beim Abrufen der Wetterdaten für Stadt: $city", e)
            throw e
        }
    }
}
