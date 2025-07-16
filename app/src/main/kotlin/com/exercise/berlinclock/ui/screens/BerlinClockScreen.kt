package com.exercise.berlinclock.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.exercise.berlinclock.model.FiveMinuteRowState
import com.exercise.berlinclock.model.FourRowState
import com.exercise.berlinclock.model.LampState

@Composable
internal fun BerlinClockScreen() {
    // This is where the Berlin Clock UI will be implemented
    // For now, we can leave it empty or add a placeholder
    Text("Berlin Clock Screen")
}

@Composable
internal fun BerlinClockContent() {
    Scaffold { internalPadding ->
        Column(modifier = Modifier.padding(internalPadding)) {
        }
    }
}


@Composable
internal fun ClockLamp(
    modifier: Modifier = Modifier,
    lampState: Boolean,
) {
    Box(
        modifier = modifier
            .height(lampsSize)
            .background(
                color = if (lampState) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.tertiary,
                shape = MaterialTheme.shapes.large
            )
    )
}


@Composable
internal fun ClockLamp(
    modifier: Modifier = Modifier,
    lampState: LampState
) {
    Box(
        modifier = modifier
            .height(lampsSize)
            .background(
                color = when (lampState) {
                    LampState.OFF -> MaterialTheme.colorScheme.tertiary
                    LampState.YELLOW -> MaterialTheme.colorScheme.secondary
                    LampState.RED -> MaterialTheme.colorScheme.primary
                },
            )
    )
}


@Composable
internal fun FourLampRow(
    modifier: Modifier = Modifier,
    lamps: FourRowState,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        lamps.lamps.forEach { lamp ->
            ClockLamp(
                modifier = Modifier.weight(1f),
                lampState = lamp
            )
        }
    }
}

@Composable
internal fun FiveMinuteRow(
    modifier: Modifier = Modifier,
    lamps: FiveMinuteRowState,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        lamps.lamps.forEach { lamp ->
            ClockLamp(
                modifier = Modifier.weight(1f),
                lampState = lamp
            )
        }
    }
}

private val lampsSize = 50.dp