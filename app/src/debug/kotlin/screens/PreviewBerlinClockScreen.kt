package screens

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.exercise.berlinclock.model.BerlinClock
import com.exercise.berlinclock.model.FiveMinuteRowState
import com.exercise.berlinclock.model.FourRowState
import com.exercise.berlinclock.model.LampState
import com.exercise.berlinclock.ui.screens.BerlinClockScreen
import com.exercise.berlinclock.ui.screens.ClockLamp
import com.exercise.berlinclock.ui.screens.FiveMinuteRow
import com.exercise.berlinclock.ui.screens.FourLampRow
import com.exercise.berlinclock.ui.theme.BerlinClockTheme

@Preview
@Composable
private fun PreviewBerlinClockScreen() {
    BerlinClockTheme {
        BerlinClockScreen(BerlinClock())
    }
}

@Preview
@Composable
private fun PreviewClockLamp() {
    BerlinClockTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ClockLamp(modifier = Modifier, lampState = true)
            ClockLamp(modifier = Modifier, lampState = false)
            ClockLamp(modifier = Modifier, lampState = false)
        }

    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun PreviewDarkClockLamp() {
    BerlinClockTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            ClockLamp(modifier = Modifier, lampState = true)
            ClockLamp(modifier = Modifier, lampState = false)
            ClockLamp(modifier = Modifier, lampState = false)
        }
    }
}

@Preview
@Composable
private fun PreviewFourLampRow() {
    BerlinClockTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FourLampRow(
                lamps = FourRowState(
                    lamps = listOf(true, false, false, false)
                )
            )
        }
    }
}

@Preview
@Composable
private fun PreviewFiveMinuteRow() {
    BerlinClockTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FiveMinuteRow(
                lamps = FiveMinuteRowState(
                    listOf(
                        LampState.YELLOW,
                        LampState.YELLOW,
                        LampState.RED,
                        LampState.OFF,
                        LampState.OFF,
                        LampState.OFF,
                        LampState.OFF,
                        LampState.OFF,
                        LampState.OFF,
                        LampState.OFF,
                        LampState.OFF
                    )
                )
            )
        }
    }
}