package com.example.baisecomposelearn.utils

fun lerp(from: Float, to: Float, amount: Float): Float {
    return from + amount * (to - from)
}