package com.exercise.berlinclock.model

import androidx.compose.runtime.Immutable

internal data class BerlinClockState(
    val secondsLamp: Boolean,
    val fiveHourRow: FourRowState,
    val singleHourRow: FourRowState,
    val fiveMinuteRow: FiveMinuteRowState,
    val singleMinuteRow: FourRowState
)

@Immutable
internal data class FourRowState(val lamps: List<Boolean>) {
    init {
        require(lamps.size == 4) { "FourRowState must have exactly 4 lamps." }
    }
}

@Immutable
internal data class FiveMinuteRowState(val lamps: List<LampState>) {
    init {
        require(lamps.size == 11) { "FiveMinuteRowState must have exactly 11 lamps." }
    }
}
