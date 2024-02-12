import javafx.concurrent.Task

class Animation(private val planet: Planet) : Task<Void>() {
    private var angle =  0.0

    override fun call(): Void? {
        while (!isCancelled) {
            // Update the angle based on the planet's speed
            angle += planet.speed

            planet.updatePosition(angle)

            // Sleep for a short duration before updating again
            Thread.sleep(100)
        }
        return null
    }
}
