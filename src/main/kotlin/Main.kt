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
    private lateinit var executorService: ExecutorService

    override fun init() {
        super.init()
        // Initialize the ExecutorService
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())
    }

    override fun start(primaryStage: Stage) {
        // Create a sun
        val sun = Circle(400.0,  400.0,  50.0, Color.YELLOW)

        // Create planets
        val mercury = Planet(100f,  0,  0, Color.GREEN,  5f)
        val venus = Planet(130f,  0,  0, Color.RED,  3f)
        val earth = Planet(170f,  0,  0, Color.BLUE,  20f)
        val mars = Planet(200f,  0,  0, Color.ORANGE,  40f)
        val jupiter = Planet(230f,  0,  0, Color.PURPLE,  7f)
        val saturn = Planet(260f,  0,  0, Color.BROWN,  25f)
        val uranus = Planet(280f,  0,  0, Color.LIGHTBLUE,  15f)
        val neptune = Planet(310f,  0,  0, Color.DARKBLUE,  17f)
        val pluto = Planet(340f,  0,  0, Color.GRAY,  32f)

        // Start the animation tasks
        val planets = listOf(mercury, venus, earth, mars, jupiter, saturn, uranus, neptune, pluto)
        for (planet in planets) {
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
        // Shutdown the ExecutorService when the application stops
        executorService.shutdown()
        super.stop()
    }
}