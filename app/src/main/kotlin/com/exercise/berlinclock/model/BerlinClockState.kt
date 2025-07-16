package com.exercise.berlinclock.model

internal data class BerlinClockState(
    val secondsLamp: Boolean,
    val fiveHourRow: List<Boolean>,
    val singleHourRow: List<Boolean>,
    val fiveMinuteRow: List<LampState>,
    val singleMinuteRow: List<Boolean>
)
