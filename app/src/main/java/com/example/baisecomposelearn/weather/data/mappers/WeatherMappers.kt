package com.example.baisecomposelearn.weather.data.mappers

import com.example.baisecomposelearn.weather.data.remote.WeatherDataDto
import com.example.baisecomposelearn.weather.data.remote.WeatherDto
import com.example.baisecomposelearn.weather.domain.info.WeatherData
import com.example.baisecomposelearn.weather.domain.info.WeatherInfo
import com.example.baisecomposelearn.weather.domain.info.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class IndexedWeatherData(
    val index:Int,
    val data:WeatherData
)

fun WeatherDataDto.toWeatherDataMap():Map<Int,List<WeatherData>>{
    return time.mapIndexed{index,time ->
        val temperature = temperatures[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeeds[index]
        val pressure = pressures[index]
        val humidity = humidities[index]
        IndexedWeatherData(
            index = index,
            data = WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                pressure = pressure,
                windSpeed = windSpeed,
                humidity =  humidity,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )
    }.groupBy{
        it.index / 24
    }.mapValues {
        it.value.map { it.data }
    }
}

fun WeatherDto.toWeatherInfo():WeatherInfo{
    val weatherDataMap = weatherData.toWeatherDataMap()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[0]?.find {
        val hour = if (now.minute < 30) now.hour else now.hour + 1
        it.time.hour == hour
    }
    return WeatherInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData
    )
}