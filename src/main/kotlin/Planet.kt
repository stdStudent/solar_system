import javafx.application.Platform
import javafx.beans.property.DoubleProperty
import javafx.beans.property.SimpleDoubleProperty
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import kotlin.math.cos
import kotlin.math.sin

data class Planet(val distanceFromSun: Float, val color: Color, val speed: Float) {
    private val sunRadius =  50.0 // assuming the sun's radius is  50 units
    private val planetScalingFactor =  0.1 // planets size relative to the sun

    // Create properties for x and y coordinates
    private val xProperty: DoubleProperty = SimpleDoubleProperty(0.0)
    private val yProperty: DoubleProperty = SimpleDoubleProperty(0.0)

    fun createCircle(): Circle {
        // Calculate the planet's radius relative to the sun
        val planetRadius = sunRadius * planetScalingFactor
        val circle =  Circle(xProperty.value, yProperty.value, planetRadius, color)

        circle.centerXProperty().bind(xProperty)
        circle.centerYProperty().bind(yProperty)

        return circle
    }

    fun updatePosition(angle: Double) {
        val centerX =  400 // Center of the window
        val centerY =  400 // Center of the window
        val radius = distanceFromSun
        val newX = centerX + radius * cos(Math.toRadians(angle))
        val newY = centerY + radius * sin(Math.toRadians(angle))

        // Update the properties instead of the circle's position by notifying the UI thread
        Platform.runLater {
            xProperty.set(newX)
            yProperty.set(newY)
        }
    }
}