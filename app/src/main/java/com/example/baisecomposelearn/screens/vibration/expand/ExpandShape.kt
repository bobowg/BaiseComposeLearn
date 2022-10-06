package com.example.baisecomposelearn.screens.vibration.expand

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

/**
 * A donut-like shape with adjustable thickness used to simulate an expanding circle.
 */
class ExpandShape(private val thickness: Float) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val outerOval = Path().apply {
            addOval(Rect(0f, 0f, size.width - 1, size.height - 1))
        }
        val ovalToSubtract = Path().apply {
            addOval(
                Rect(
                    thickness,
                    thickness,
                    right = size.width - 1 - thickness,
                    bottom = size.height - 1 - thickness
                )
            )
        }
        val resultPath = Path()
        // Create a donut shape by subtracting the the smaller oval from the larger one.
        resultPath.op(outerOval, ovalToSubtract, PathOperation.Difference)
        return Outline.Generic(resultPath)
    }
}
