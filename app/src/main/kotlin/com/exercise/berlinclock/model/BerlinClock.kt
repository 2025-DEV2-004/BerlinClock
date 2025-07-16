package com.exercise.berlinclock.model

import kotlinx.datetime.LocalTime

internal class BerlinClock {

    internal fun getSecondsLamp(time: LocalTime): Boolean {
        val seconds = time.second
        return seconds % 2 == 0
    }

    internal fun getSingleMinuteRow(time: LocalTime): List<Boolean> {
        val minutes = time.minute
        val singleMinutes = minutes % 5
        return List(4) { it < singleMinutes }
    }

    internal fun getFiveMinuteRow(time: LocalTime): List<LampState> {
        val minutes = time.minute
        val fiveMinuteBlocks = minutes / 5
        return List(11) { index ->
            if (index < fiveMinuteBlocks) {
                if ((index + 1) % 3 == 0) LampState.RED else LampState.YELLOW
            } else {
                LampState.OFF
            }
        }
    }

    internal fun getSingleHourRow(time: LocalTime): List<Boolean> {
        val hours = time.hour
        val singleHours = hours % 5
        return List(4) { index -> index < singleHours }
    }

    internal fun getFiveHourRow(time: LocalTime): List<Boolean> {
        val hours = time.hour
        val fiveHourBlocks = hours / 5
        return List(4) { index -> index < fiveHourBlocks }
    }

    internal fun getBerlinClockState(time: LocalTime): BerlinClockState {
        return BerlinClockState(
            secondsLamp = getSecondsLamp(time),
            fiveHourRow = getFiveHourRow(time),
            singleHourRow = getSingleHourRow(time),
            fiveMinuteRow = getFiveMinuteRow(time),
            singleMinuteRow = getSingleMinuteRow(time)
        )
    }
}