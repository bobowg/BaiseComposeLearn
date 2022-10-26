package com.example.baisecomposelearn.weather.domain.location

import android.location.Location

interface LocationTracker {
    suspend fun getCurrentLocation():Location?
}
