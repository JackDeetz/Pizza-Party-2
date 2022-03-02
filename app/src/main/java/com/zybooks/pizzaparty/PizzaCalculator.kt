package com.zybooks.pizzaparty

/*
The Model of the app (backend/sensitive data)
 */

import kotlin.math.ceil

const val SLICES_PER_PIZZA = 8

// class constructor, arguments (number of people eating pizza, hungerLevel enum selection)
class PizzaCalculator(partySize: Int, var hungerLevel: HungerLevel) {

    var partySize = 0
        set(value) {
            field = if (value >= 0) value else 0 //setter floor guard
        }
    //enum options are displayed in the View, converts vague statement about hunger to number of slices
    enum class HungerLevel(var numSlices: Int) {
        LIGHT(2), MEDIUM(3), RAVENOUS(4)
    }

    //returns the number of people eating by a number of slices per person, then divides the result by the number of slices in a pizza,
    //                                                                      rounding up to the next whole number (no half pizzas sold)
    val totalPizzas: Int
        get() {     //ceiling of the number of pizza, then converted to an int
            return ceil(partySize * hungerLevel.numSlices / SLICES_PER_PIZZA.toDouble()).toInt()
        }

    //init block is run first when class is instantiated
    init {
        this.partySize = partySize
    }
}