package com.example.baisecomposelearn.weather.domain.info

import java.time.LocalDateTime

data class WeatherInfo(
    val weatherDataPerDay: Map<Int, List<WeatherData>>,
    val currentWeatherData: WeatherData?
)

data class WeatherData(
    val time:LocalDateTime,
    val temperatureCelsius:Double,
    val pressure:Double,
    val windSpeed:Double,
    val humidity:Double,
    val weatherType:WeatherType
)

