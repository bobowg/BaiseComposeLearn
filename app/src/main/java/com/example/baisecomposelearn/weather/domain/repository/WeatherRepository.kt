package com.example.baisecomposelearn.weather.domain.repository

import com.bumptech.glide.load.engine.Resource
import com.example.baisecomposelearn.weather.domain.info.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat:Double,long:Double): com.example.baisecomposelearn.weather.domain.util.Resource<WeatherInfo>
}