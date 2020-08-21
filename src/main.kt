package parking
import java.util.*
val scanner = Scanner(System.`in`)

fun main() {
    Parking.start()
}

object Parking {
    val spotCount = 20
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
                        println("Sorry, the parking lot is full.")
                    } else {
                        spots[freeSpot].car = Car(regNumber, carColor)
                        println("$carColor car parked in spot ${freeSpot + 1}.")
                    }
                }
                "leave" -> {
                    val spotNumber = scanner.nextInt()
                    if (spots[spotNumber - 1].isEmpty) {
                        println("There is no car in spot $spotNumber.")
                    } else {
                        println("Spot $spotNumber is free.")
                        spots[spotNumber - 1].car = null
                    }
                }
                "exit" -> working = false
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
