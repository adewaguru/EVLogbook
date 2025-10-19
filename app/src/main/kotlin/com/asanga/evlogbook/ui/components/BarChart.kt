package com.asanga.evlogbook.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import kotlin.math.max
import kotlin.math.min

@Composable
fun BarChart(
    data: List<Double>,
    labels: List<String> = emptyList(),
    modifier: Modifier = Modifier,
    barColor: Color = MaterialTheme.colorScheme.primary,
    backgroundColor: Color = MaterialTheme.colorScheme.surface
) {
    if (data.isEmpty()) {
        Canvas(modifier = modifier) {
            // Draw empty state
        }
        return
    }

    val maxValue = data.maxOrNull() ?: 0.0
    val minValue = data.minOrNull() ?: 0.0
    val range = if (maxValue == minValue) maxValue else maxValue - minValue
    val outlineColor = MaterialTheme.colorScheme.outline

    Canvas(modifier = modifier) {
        val width = size.width
        val height = size.height
        val barWidth = width / data.size * 0.8f
        val spacing = width / data.size * 0.2f

        // Draw bars
        data.forEachIndexed { index, value ->
            val barHeight = if (range == 0.0) {
                height * 0.5f
            } else {
                ((value - minValue) / range * height * 0.8f).toFloat()
            }

            val x = index * (barWidth + spacing) + spacing / 2
            val y = height - barHeight

            drawRect(
                color = barColor,
                topLeft = androidx.compose.ui.geometry.Offset(x, y),
                size = androidx.compose.ui.geometry.Size(barWidth, barHeight)
            )
        }

        // Draw grid lines (optional)
        drawLine(
            color = outlineColor.copy(alpha = 0.3f),
            start = androidx.compose.ui.geometry.Offset(0f, height / 2),
            end = androidx.compose.ui.geometry.Offset(width, height / 2),
            strokeWidth = 0.5.dp.toPx()
        )
    }
}
