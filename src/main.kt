package parking
import java.util.*
val scanner = Scanner(System.`in`)

fun main() {
    Parking.start()
}

object Parking {
    private val spots: MutableList<Spot> = mutableListOf()
    private var working = true
    fun start(){
        while (working) {

            when (scanner.next()!!) {
                "park" -> {
                    val regNumber = scanner.next()!!
                    val carColor = scanner.next()!!
                    if (spots.isEmpty())  {
                        println("Sorry, a parking lot has not been created.")
                    } else {
                        val freeSpot = findEmptySpot()
                        if (freeSpot == -1) {
                            println("Sorry, the parking lot is full.")
                        } else {
                            spots[freeSpot].car = Car(regNumber, carColor)
                            println("$carColor car parked in spot ${freeSpot + 1}.")
                        }
                    }
                }
                "leave" -> {
                    val spotNumber = scanner.nextInt()
                    if (spots.isEmpty()) {
                        println("Sorry, a parking lot has not been created.")
                    } else {
                        if (spots[spotNumber - 1].isEmpty) {
                            println("There is no car in spot $spotNumber.")
                        } else {
                            println("Spot $spotNumber is free.")
                            spots[spotNumber - 1].car = null
                        }
                    }
                }
                "create" -> {
                    val spotNumber = scanner.nextInt()
                    createParking(spotNumber)
                }
                "status" -> {
                    if (spots.isEmpty())  {
                        println("Sorry, a parking lot has not been created.")
                    } else {
                        printStatus()
                    }
                }
                "reg_by_color" -> {
                    val carColor = scanner.next()!!
                    if (spots.isEmpty())  {
                        println("Sorry, a parking lot has not been created.")
                    } else {
                        findCarsByColor(carColor)
                    }
                }
                "spot_by_color" -> {
                    val carColor = scanner.next()!!
                    if (spots.isEmpty())  {
                        println("Sorry, a parking lot has not been created.")
                    } else {
                        findSpotsByColor(carColor)
                    }
                }
                "spot_by_reg" -> {
                    val regNumber = scanner.next()!!
                    if (spots.isEmpty())  {
                        println("Sorry, a parking lot has not been created.")
                    } else {
                        findSpotsByReg(regNumber)
                    }
                }
                "exit" -> working = false
                else -> println("wrong action")
            }
        }
    }

    private fun findSpotsByReg(regNumber: String) {
        var first = true
        spots.forEachIndexed { index, spot ->
            if (spot.car?.regNumber == regNumber) {
                if (!first) print(", ")
                print(index + 1)
                first = false
            }
        }
        if (first) println("No cars with registration number $regNumber were found.")
        else println()
    }

    private fun findSpotsByColor(carColor: String) {
        val tempColorLowerCase = carColor.toLowerCase()
        var first = true
        spots.forEachIndexed { index, spot ->
            if (spot.car?.carColor?.toLowerCase() == tempColorLowerCase) {
                if (!first) print(", ")
                print(index + 1)
                first = false
            }
        }
        if (first) println("No cars with color $carColor were found.")
        else println()
    }

    private fun findCarsByColor(carColor: String) {
        val tempColorLowerCase = carColor.toLowerCase()
        val cars = spots.filter { it.car?.carColor?.toLowerCase() == tempColorLowerCase }
        var first = true
        cars.forEach {
            if (!first) print(", ")
            print(it.car?.regNumber)
            first = false
        }
        if (cars.isEmpty()) println("No cars with color $carColor were found.")
        else println()
    }


    private fun printStatus(){
        var empty = true
        spots.forEachIndexed { index, spot ->
            if (!spot.isEmpty) {
                println("${index + 1} ${spot.car!!.regNumber} ${spot.car!!.carColor}")
                empty = false
            }
        }
        if (empty) println("Parking lot is empty.")
    }

    private fun findEmptySpot(): Int {
        return spots.indexOfFirst { it.isEmpty } }

    private fun createParking(count: Int) {
        spots.clear()
        repeat(count) {
            spots.add(Spot(null))
        }
        println("Created a parking lot with $count spots.")
    }
}

data class Car(var regNumber: String, var carColor: String)

class Spot(var car:Car?) {
    val isEmpty: Boolean
        get() = car == null
}
