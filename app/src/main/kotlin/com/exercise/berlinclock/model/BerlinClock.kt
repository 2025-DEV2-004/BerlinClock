package com.exercise.berlinclock.model

class BerlinClock {

    fun getSecondsLamp(time: String): String {
        val seconds = time.split(":")[2].toInt()
        return if (seconds % 2 == 0) "Y" else "O"
    }

}