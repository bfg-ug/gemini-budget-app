import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun CircularRingChart(
    data: List<Pair<Float, Color>>,
    total: Float
) {
    val totalAngle = 360f
    var startAngle = -90f

    Column(
        modifier = Modifier.padding(vertical = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(200.dp)
                .padding(16.dp)
        ) {
            Canvas(
                modifier = Modifier.fillMaxSize()
            ) {
                data.forEach { (percentage, color) ->
                    val sweepAngle = totalAngle * (percentage / total)
                    drawArc(
                        color = color,
                        startAngle = startAngle,
                        sweepAngle = sweepAngle,
                        useCenter = false,
                        topLeft = Offset(0f, 0f),
                        size = Size(size.width, size.height),
                        style = Stroke(16.dp.toPx())
                    )
                    startAngle += sweepAngle
                }
            }
            Text(
                text = "$total",
                fontSize = 40.sp,
                textAlign = TextAlign.Center,

            )
        }
    }
}

@Preview
@Composable
fun PreviewCircularRingChart() {
    val data = listOf(
        20f to Color.Blue,
        30f to Color.Green,
        10f to Color.Red,
        40f to Color.Magenta
    )
    CircularRingChart(data = data, total = 100f)
}
