package com.example.baisecomposelearn.weather.data.repository

import com.example.baisecomposelearn.weather.data.mappers.toWeatherInfo
import com.example.baisecomposelearn.weather.domain.util.Resource
import com.example.baisecomposelearn.weather.data.remote.WeatherApi
import com.example.baisecomposelearn.weather.domain.info.WeatherInfo
import com.example.baisecomposelearn.weather.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
): WeatherRepository {
    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api.getWeatherData(
                    lat = lat,
                    long = long
                ).toWeatherInfo()
            )
        } catch(e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "发生未知错误。")
        }
    }
}