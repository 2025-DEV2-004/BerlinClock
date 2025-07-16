package com.exercise.berlinclock.model

class BerlinClock {

    fun getSecondsLamp(time: String): Boolean {
        val seconds = time.split(":")[2].toInt()
        return seconds % 2 == 0
    }

}