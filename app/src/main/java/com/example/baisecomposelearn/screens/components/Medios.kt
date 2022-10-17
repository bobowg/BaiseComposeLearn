package com.example.baisecomposelearn.screens.components

internal fun randomSampleImageUrl(
    seed: Int = ((Math.random() * 9 + 1) * 1000000).toInt(),
    width: Int = 300,
): String {
    return "https://images.pexels.com/photos/$seed/pexels-photo-$seed.jpeg?auto=compress&cs=tinysrgb&h=$width"
}