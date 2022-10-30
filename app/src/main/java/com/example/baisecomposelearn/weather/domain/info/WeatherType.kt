package com.example.baisecomposelearn.weather.domain.info

import androidx.annotation.DrawableRes
import com.example.baisecomposelearn.R

sealed class WeatherType(
    val weatherDesc:String,
    @DrawableRes val iconRes:Int
){
    object ClearSky:WeatherType(
        weatherDesc = "晴天",
        iconRes = R.drawable.ic_sunny
    )
    object MainlyClear:WeatherType(
        weatherDesc = "多云为主",
        iconRes = R.drawable.ic_cloudy
    )
    object PartlyCloudy:WeatherType(
        weatherDesc = "部分多云",
        iconRes = R.drawable.ic_cloudy
    )
    object Overcast:WeatherType(
        weatherDesc = "多云",
        iconRes = R.drawable.ic_cloudy
    )
    object Foggy:WeatherType(
        weatherDesc = "雾",
        iconRes = R.drawable.ic_very_cloudy
    )
    object DepositingRimeFog:WeatherType(
        weatherDesc = "沉积雾凇雾",
        iconRes = R.drawable.ic_very_cloudy
    )
    object LightDrizzle:WeatherType(
        weatherDesc = "毛毛雨",
        iconRes = R.drawable.ic_rainshower
    )
    object ModerateDrizzle:WeatherType(
        weatherDesc = "中度毛毛雨",
        iconRes = R.drawable.ic_rainshower
    )
    object DenseDrizzle:WeatherType(
        weatherDesc = "浓密的毛毛雨",
        iconRes = R.drawable.ic_rainshower
    )
    object LightFreezingDrizzle:WeatherType(
        weatherDesc = "轻度冷冻毛毛雨",
        iconRes = R.drawable.ic_snowyrainy
    )
    object DenseFreezingDrizzle:WeatherType(
        weatherDesc = "中度冷冻毛毛雨",
        iconRes = R.drawable.ic_snowyrainy
    )
    object SlightRain:WeatherType(
        weatherDesc = "小雨",
        iconRes = R.drawable.ic_rainy
    )
    object ModerateRain:WeatherType(
        weatherDesc = "中雨",
        iconRes = R.drawable.ic_rainy
    )
    object HeavyRain:WeatherType(
        weatherDesc = "大雨",
        iconRes = R.drawable.ic_rainy
    )
    object HeavyFreezingRain:WeatherType(
        weatherDesc = "大冰雨",
        iconRes = R.drawable.ic_snowyrainy
    )
    object SlightSnowFall:WeatherType(
        weatherDesc = "轻微的降雪",
        iconRes = R.drawable.ic_snowy
    )
    object ModerateSnowFall:WeatherType(
        weatherDesc = "中度降雪",
        iconRes = R.drawable.ic_heavysnow
    )
    object HeavySnowFall:WeatherType(
        weatherDesc = "大雪",
        iconRes = R.drawable.ic_heavysnow
    )
    object SnowGrains:WeatherType(
        weatherDesc = "雪粒",
        iconRes = R.drawable.ic_heavysnow
    )
    object SlightRainShowers:WeatherType(
        weatherDesc = "轻微的阵雨",
        iconRes = R.drawable.ic_rainshower
    )
    object ModerateRainShowers:WeatherType(
        weatherDesc = "中度阵雨",
        iconRes = R.drawable.ic_rainshower
    )
    object ViolentRainShowers:WeatherType(
        weatherDesc = "暴雨阵雨",
        iconRes = R.drawable.ic_rainshower
    )
    object SlightSnowShowers:WeatherType(
        weatherDesc = "轻微的阵雨",
        iconRes = R.drawable.ic_snowy
    )
    object HeavySnowShowers:WeatherType(
        weatherDesc = "大雪转阵雨",
        iconRes = R.drawable.ic_snowy
    )
    object ModerateThunderstorm:WeatherType(
        weatherDesc = "中度雷暴",
        iconRes = R.drawable.ic_thunder
    )
    object SlightHailThunderstorm:WeatherType(
        weatherDesc = "轻微的海雷暴",
        iconRes = R.drawable.ic_rainythunder
    )
    object HeavyHailThunderstorm:WeatherType(
        weatherDesc = "大冰雹雷暴",
        iconRes = R.drawable.ic_rainythunder
    )

    companion object{
        fun fromWMO(code:Int):WeatherType{
            return when(code){
                0 -> ClearSky
                1 -> MainlyClear
                2 -> PartlyCloudy
                3 -> Overcast
                45 -> Foggy
                48 -> DepositingRimeFog
                51 -> LightDrizzle
                53 -> ModerateDrizzle
                55 -> DenseDrizzle
                56 -> LightFreezingDrizzle
                57 -> DenseFreezingDrizzle
                61 -> SlightRain
                63 -> ModerateRain
                65 -> HeavyRain
                66 -> LightFreezingDrizzle
                67 -> HeavyFreezingRain
                71 -> SlightSnowFall
                73 -> ModerateSnowFall
                75 -> HeavySnowFall
                77 -> SnowGrains
                80 -> SlightRainShowers
                81 -> ModerateRainShowers
                82 -> ViolentRainShowers
                85 -> SlightSnowShowers
                86 -> HeavySnowShowers
                95 -> ModerateThunderstorm
                96 -> SlightHailThunderstorm
                99 -> HeavyHailThunderstorm
                else -> ClearSky
            }
        }
    }

}
