import javafx.application.Application
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.paint.Color
import javafx.scene.shape.Circle
import javafx.stage.Stage
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

fun main(args: Array<String>) {
    Application.launch(MyApp::class.java, *args)
}

class MyApp : Application() {
    private val executorServices: MutableList<ExecutorService> = mutableListOf()

    override fun start(primaryStage: Stage) {
        // Create the Sun
        val sun = Circle(400.0,  400.0,  50.0, Color.YELLOW)

        // Create planets
        val mercury = Planet(100f, Color.GREEN,  5f)
        val venus = Planet(130f, Color.RED,  3f)
        val earth = Planet(170f, Color.BLUE,  20f)
        val mars = Planet(200f, Color.ORANGE,  40f)
        val jupiter = Planet(230f, Color.PURPLE,  7f)
        val saturn = Planet(260f, Color.BROWN,  25f)
        val uranus = Planet(280f, Color.LIGHTBLUE,  15f)
        val neptune = Planet(310f, Color.DARKBLUE,  17f)
        val pluto = Planet(340f, Color.GRAY,  32f)

        // Start the animation tasks
        val planets = listOf(mercury, venus, earth, mars, jupiter, saturn, uranus, neptune, pluto)
        for (planet in planets) {
            val executorService = Executors.newSingleThreadExecutor()
            executorServices.add(executorService)
            val task = Animation(planet)
            executorService.submit(task)
        }

        // Add all planets and the sun to a group
        val group = Group(sun, *planets.map { it.createCircle() }.toTypedArray())

        // Set up the scene and show the stage
        val scene = Scene(group,  800.0,  800.0)
        primaryStage.title = "Solar System Animation"
        primaryStage.scene = scene
        primaryStage.show()
    }

    override fun stop() {
        // Shutdown the ExecutorServices when the application stops
        for (executorService in executorServices)
            executorService.shutdownNow()

        super.stop()
    }
}