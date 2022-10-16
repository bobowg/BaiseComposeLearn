package com.example.baisecomposelearn.screens.components

internal fun randomSampleImageUrl(
    seed: Int =(1000000 until  9999999).random(),
    width: Int = 300,
): String {
    return "https://images.pexels.com/photos/$seed/pexels-photo-$seed.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=$width"
}