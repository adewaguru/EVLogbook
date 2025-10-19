package com.asanga.evlogbook.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.max
import kotlin.math.min

@Composable
fun StatCard(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.height(80.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = title,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun SparklineChart(
    data: List<Double>,
    modifier: Modifier = Modifier,
    lineColor: Color = MaterialTheme.colorScheme.primary,
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
        val strokeWidth = 2.dp.toPx()

        // Draw background grid lines (optional)
        drawLine(
            color = outlineColor.copy(alpha = 0.3f),
            start = Offset(0f, height / 2),
            end = Offset(width, height / 2),
            strokeWidth = 0.5.dp.toPx()
        )

        // Create path for the line
        val path = Path()
        val stepX = width / (data.size - 1).coerceAtLeast(1)

        data.forEachIndexed { index, value ->
            val x = index * stepX
            val y = if (range == 0.0) {
                height / 2
            } else {
                height - ((value - minValue) / range * height * 0.8 + height * 0.1).toFloat()
            }

            if (index == 0) {
                path.moveTo(x, y)
            } else {
                path.lineTo(x, y)
            }
        }

        // Draw the line
        drawPath(
            path = path,
            color = lineColor,
            style = Stroke(
                width = strokeWidth,
                cap = StrokeCap.Round
            )
        )

        // Draw data points (optional)
        data.forEachIndexed { index, value ->
            val x = index * stepX
            val y = if (range == 0.0) {
                height / 2
            } else {
                height - ((value - minValue) / range * height * 0.8 + height * 0.1).toFloat()
            }

            drawCircle(
                color = lineColor,
                radius = 2.dp.toPx(),
                center = Offset(x, y)
            )
        }
    }
}
