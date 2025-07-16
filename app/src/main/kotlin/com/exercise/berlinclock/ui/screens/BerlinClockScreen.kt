package com.exercise.berlinclock.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import com.exercise.berlinclock.R
import com.exercise.berlinclock.model.BerlinClock
import com.exercise.berlinclock.model.BerlinClockState
import com.exercise.berlinclock.model.FiveMinuteRowState
import com.exercise.berlinclock.model.FourRowState
import com.exercise.berlinclock.model.LampState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalTime
import kotlinx.datetime.TimeZone.Companion.currentSystemDefault
import kotlinx.datetime.toLocalDateTime

private fun getCurrentLocalTime(): LocalTime {
    return Clock.System.now().toLocalDateTime(currentSystemDefault()).time
}

@Composable
internal fun BerlinClockScreen(
    berlinClock: BerlinClock
) {
    var currentTime by remember {
        mutableStateOf(
            getCurrentLocalTime()
        )
    }
    var berlinClockState by remember { mutableStateOf(berlinClock.getBerlinClockState(currentTime)) }

    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(lifecycleOwner, berlinClock) { // Key on lifecycleOwner and berlinClock
        // Create a flow that emits every second
        val tickerFlow = flow {
            while (true) {
                emit(Unit) // Emit a signal
                delay(1000)
            }
        }

        // Collect the flow only when the lifecycle is in the STARTED state
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            tickerFlow.collect {
                // This block will run when the lifecycle is STARTED
                // and will be cancelled when it moves to STOPPED.
                currentTime = getCurrentLocalTime()
                berlinClockState = berlinClock.getBerlinClockState(currentTime)
            }
        }
        // When repeatOnLifecycle completes (lifecycle moves out of STARTED),
        // the tickerFlow collection stops.
        // If the Composable is destroyed, LaunchedEffect is cancelled, stopping everything.
    }

    BerlinClockContent(
        berlinClockState = berlinClockState,
        time = berlinClock.toString(currentTime)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun BerlinClockContent(
    modifier: Modifier = Modifier,
    berlinClockState: BerlinClockState,
    time: String
) {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(text = stringResource(R.string.app_name))
            }
        )
    }) { internalPadding ->
        Column(
            modifier = Modifier
                .padding(internalPadding)
                .windowInsetsPadding(WindowInsets.safeDrawing)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ClockLamp(
                modifier.width(50.dp),
                lampState = berlinClockState.secondsLamp
            )
            FourLampRow(
                lamps = berlinClockState.fiveHourRow
            )
            FourLampRow(
                lamps = berlinClockState.singleHourRow
            )
            FiveMinuteRow(
                lamps = berlinClockState.fiveMinuteRow
            )
            FourLampRow(
                lamps = berlinClockState.singleMinuteRow
            )
            Text(
                style = MaterialTheme.typography.headlineLarge,
                text = time
            )
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
                shape = MaterialTheme.shapes.medium
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

private
val lampsSize = 50.dp