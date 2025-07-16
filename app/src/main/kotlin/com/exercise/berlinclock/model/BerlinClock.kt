package com.exercise.berlinclock.model

internal class BerlinClock {

    internal fun getSecondsLamp(time: String): Boolean {
        val seconds = time.split(":")[2].toInt()
        return seconds % 2 == 0
    }

    internal fun getSingleMinuteRow(time: String): List<Boolean> {
        val minutes = time.split(":")[1].toInt()
        val singleMinutes = minutes % 5
        return List(4) { it < singleMinutes }
    }

    internal fun getFiveMinuteRow(time: String): List<LampState> {
        val minutes = time.split(":")[1].toInt()
        val fiveMinuteBlocks = minutes / 5
        return List(11) { index ->
            if (index < fiveMinuteBlocks) {
                if ((index + 1) % 3 == 0) LampState.RED else LampState.YELLOW
            } else {
                LampState.OFF
            }
        }
    }

    internal fun getSingleHourRow(time: String): List<Boolean> {
        val hours = time.split(":")[0].toInt()
        val singleHours = hours % 5
        return List(4) { index -> index < singleHours }
    }

    fun getFiveHourRow(time: String): List<Boolean> {
        val hours = time.split(":")[0].toInt()
        val fiveHourBlocks = hours / 5
        return List(4) { index -> index < fiveHourBlocks }
    }
}