import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

data class Planet(val distanceFromSun: Float, var x: Int, var y: Int, val color: Color, val speed: Float) {
    private lateinit var circle: Circle

    // Assuming the sun's radius is  50 units
    private val sunRadius =  50.0
    private val planetScalingFactor =  0.1 // Adjust this to make the planet appear smaller

    fun createCircle(): Circle {
        // Calculate the planet's radius relative to the sun
        val planetRadius = sunRadius * planetScalingFactor
        return Circle(x.toDouble(), y.toDouble(), planetRadius, color).apply { circle = this }
    }

    fun updateCircle() {
        circle.centerX = x.toDouble()
        circle.centerY = y.toDouble()
    }

    fun updatePosition(angle: Double) {
        val centerX =  400 // Center of the window
        val centerY =  400 // Center of the window
        val radius = distanceFromSun
        val newX = centerX + radius * cos(Math.toRadians(angle)).toFloat()
        val newY = centerY + radius * sin(Math.toRadians(angle)).toFloat()

        x = newX.roundToInt()
        y = newY.roundToInt()

        updateCircle()
    }
}