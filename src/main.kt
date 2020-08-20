package parking
import java.util.*
val scanner = Scanner(System.`in`)

fun main() {
    Parking.start()
}

object Parking {
    val spotCount = 2
    val spots: MutableList<Spot> = mutableListOf()
    var working = true
    fun start(){
        repeat(spotCount) {
            spots.add(Spot(null))
        }

        while (working) {
            val action = scanner.next()!!

            when (action) {
                "park" -> {
                    val regNumber = scanner.next()!!
                    val carColor = scanner.next()!!
                    val freeSpot = findEmptySpot()
                    if (freeSpot == -1) {
                        println("There is no free spot")
                    } else {
                        spots.set(freeSpot, Spot(Car(regNumber, carColor)))
                        println("$carColor car parked in spot ${freeSpot + 1}.")
                    }
                }
                else -> println("wrong action")
            }
        }
    }

    private fun findEmptySpot(): Int {
        return spots.indexOfFirst { it.isEmpty } }
}

class Car(var regNumber: String, var carColor: String) {

}

class Spot(var car:Car?) {
    val isEmpty: Boolean
        get() = car == null
}
