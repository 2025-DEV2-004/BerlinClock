package com.exercise.berlinclock

import com.exercise.berlinclock.model.BerlinClock
import com.exercise.berlinclock.model.BerlinClockState
import com.exercise.berlinclock.model.LampState
import org.junit.Assert
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test


class BerlinClockTest {

    private lateinit var berlinClock: BerlinClock

    @Before
    fun setup() {
        berlinClock = BerlinClock()
    }

    @Test
    fun `secondsLamp should be true for even seconds`() {
        val time = "00:00:00" // Even second
        assertTrue(berlinClock.getSecondsLamp(time))
    }

    @Test
    fun `secondsLamp should be false for odd seconds`() {
        val time = "00:00:01" // Odd second
        assertFalse(berlinClock.getSecondsLamp(time))
    }

    @Test
    fun `singleMinuteRow should be OFF OFF OFF OFF for 0 minutes`() {
        val time = "00:00:00"
        val expected = listOf(false, false, false, false)
        Assert.assertEquals(expected, berlinClock.getSingleMinuteRow(time))
    }

    @Test
    fun `singleMinuteRow should be ON OFF OFF OFF for 1 minute`() {
        val time = "00:01:00"
        val expected = listOf(true, false, false, false)
        Assert.assertEquals(expected, berlinClock.getSingleMinuteRow(time))
    }

    @Test
    fun `singleMinuteRow should be ON ON OFF OFF for 2 minutes`() {
        val time = "00:02:00"
        val expected = listOf(true, true, false, false)
        Assert.assertEquals(expected, berlinClock.getSingleMinuteRow(time))
    }

    @Test
    fun `singleMinuteRow should be ON ON ON OFF for 3 minutes`() {
        val time = "00:03:00"
        val expected = listOf(true, true, true, false)
        Assert.assertEquals(expected, berlinClock.getSingleMinuteRow(time))
    }

    @Test
    fun `singleMinuteRow should be ON ON ON ON for 4 minutes`() {
        val time = "00:04:00"
        val expected = listOf(true, true, true, true)
        Assert.assertEquals(expected, berlinClock.getSingleMinuteRow(time))
    }

    @Test
    fun `singleMinuteRow should be OFF OFF OFF OFF for 5 minutes`() { // Rollover
        val time = "00:05:00"
        val expected = listOf(false, false, false, false)
        Assert.assertEquals(expected, berlinClock.getSingleMinuteRow(time))
    }

    @Test
    fun `singleMinuteRow should be ON OFF OFF OFF for 56 minutes`() {
        val time = "00:56:00" // 56 % 5 = 1
        val expected = listOf(true, false, false, false)
        Assert.assertEquals(expected, berlinClock.getSingleMinuteRow(time))
    }

    @Test
    fun `fiveMinuteRow should be all OFF for 0 minutes`() {
        val time = "00:00:00"
        val expected = List(11) { LampState.OFF }
        Assert.assertEquals(expected, berlinClock.getFiveMinuteRow(time))
    }

    @Test
    fun `fiveMinuteRow should be all OFF for 4 minutes`() { // less than 5
        val time = "00:04:00"
        val expected = List(11) { LampState.OFF }
        Assert.assertEquals(expected, berlinClock.getFiveMinuteRow(time))
    }

    @Test
    fun `fiveMinuteRow for 5 minutes`() {
        val time = "00:05:00"
        val expected = listOf(
            LampState.YELLOW, LampState.OFF, LampState.OFF, LampState.OFF, LampState.OFF,
            LampState.OFF, LampState.OFF, LampState.OFF, LampState.OFF, LampState.OFF, LampState.OFF
        )
        Assert.assertEquals(expected, berlinClock.getFiveMinuteRow(time))
    }

    @Test
    fun `fiveMinuteRow for 15 minutes`() {
        val time = "00:15:00"
        val expected = listOf(
            LampState.YELLOW, LampState.YELLOW, LampState.RED, LampState.OFF, LampState.OFF,
            LampState.OFF, LampState.OFF, LampState.OFF, LampState.OFF, LampState.OFF, LampState.OFF
        )
        Assert.assertEquals(expected, berlinClock.getFiveMinuteRow(time))
    }

    @Test
    fun `fiveMinuteRow for 37 minutes`() {
        val time = "00:37:00" // 37 / 5 = 7 lamps
        val expected = listOf(
            LampState.YELLOW, LampState.YELLOW, LampState.RED,    // 15
            LampState.YELLOW, LampState.YELLOW, LampState.RED,    // 30
            LampState.YELLOW, LampState.OFF, LampState.OFF,    // 35
            LampState.OFF, LampState.OFF
        )
        Assert.assertEquals(expected, berlinClock.getFiveMinuteRow(time))
    }


    @Test
    fun `fiveMinuteRow for 59 minutes`() {
        val time = "00:59:59" // 59 / 5 = 11 lamps
        val expected = listOf(
            LampState.YELLOW, LampState.YELLOW, LampState.RED,    // 15
            LampState.YELLOW, LampState.YELLOW, LampState.RED,    // 30
            LampState.YELLOW, LampState.YELLOW, LampState.RED,    // 45
            LampState.YELLOW, LampState.YELLOW                     // 55
        )
        Assert.assertEquals(expected, berlinClock.getFiveMinuteRow(time))
    }


    @Test
    fun `singleHourRow should be all OFF for 0 hours`() {
        val time = "00:00:00"
        val expected = listOf(false, false, false, false)
        Assert.assertEquals(expected, berlinClock.getSingleHourRow(time))
    }

    @Test
    fun `singleHourRow should be R000 for 1 hour`() { // R being true
        val time = "01:00:00"
        val expected = listOf(true, false, false, false)
        Assert.assertEquals(expected, berlinClock.getSingleHourRow(time))
    }

    @Test
    fun `singleHourRow should be RRR0 for 3 hours`() {
        val time = "03:00:00"
        val expected = listOf(true, true, true, false)
        Assert.assertEquals(expected, berlinClock.getSingleHourRow(time))
    }

    @Test
    fun `singleHourRow should be RRRR for 4 hours`() {
        val time = "04:00:00"
        val expected = listOf(true, true, true, true)
        Assert.assertEquals(expected, berlinClock.getSingleHourRow(time))
    }

    @Test
    fun `singleHourRow should be all OFF for 5 hours (rollover)`() {
        val time = "05:00:00"
        val expected = listOf(false, false, false, false)
        Assert.assertEquals(expected, berlinClock.getSingleHourRow(time))
    }

    @Test
    fun `singleHourRow should be R000 for 6 hours`() { // 6 % 5 = 1
        val time = "06:00:00"
        val expected = listOf(true, false, false, false)
        Assert.assertEquals(expected, berlinClock.getSingleHourRow(time))
    }

    @Test
    fun `singleHourRow should be RRRR for 19 hours`() { // 19 % 5 = 4
        val time = "19:00:00"
        val expected = listOf(true, true, true, true)
        Assert.assertEquals(expected, berlinClock.getSingleHourRow(time))
    }

    @Test
    fun `singleHourRow should be RRR0 for 23 hours`() { // 23 % 5 = 3 (careful, it's 23 mod 5, not 23 / 5)
        val time = "23:59:59" // 23 % 5 = 3
        val expected = listOf(true, true, true, false) // This should be 3 lamps on for 23 % 5 = 3
        Assert.assertEquals(expected, berlinClock.getSingleHourRow(time))
    }

    @Test
    fun `fiveHourRow should be all OFF for 0 hours`() {
        val time = "00:00:00"
        val expected = listOf(false, false, false, false)
        Assert.assertEquals(expected, berlinClock.getFiveHourRow(time))
    }

    @Test
    fun `fiveHourRow should be all OFF for 4 hours`() { // Less than 5
        val time = "04:00:00"
        val expected = listOf(false, false, false, false)
        Assert.assertEquals(expected, berlinClock.getFiveHourRow(time))
    }

    @Test
    fun `fiveHourRow should be R000 for 5 hours`() {
        val time = "05:00:00"
        val expected = listOf(true, false, false, false)
        Assert.assertEquals(expected, berlinClock.getFiveHourRow(time))
    }

    @Test
    fun `fiveHourRow should be R000 for 9 hours`() { // 9 / 5 = 1
        val time = "09:59:59"
        val expected = listOf(true, false, false, false)
        Assert.assertEquals(expected, berlinClock.getFiveHourRow(time))
    }

    @Test
    fun `fiveHourRow should be RR00 for 10 hours`() {
        val time = "10:00:00"
        val expected = listOf(true, true, false, false)
        Assert.assertEquals(expected, berlinClock.getFiveHourRow(time))
    }

    @Test
    fun `fiveHourRow should be RRR0 for 17 hours`() { // 17 / 5 = 3
        val time = "17:30:00"
        val expected = listOf(true, true, true, false)
        Assert.assertEquals(expected, berlinClock.getFiveHourRow(time))
    }

    @Test
    fun `fiveHourRow should be RRRR for 20 hours`() {
        val time = "20:00:00"
        val expected = listOf(true, true, true, true)
        Assert.assertEquals(expected, berlinClock.getFiveHourRow(time))
    }

    @Test
    fun `fiveHourRow should be RRRR for 23 hours`() { // 23 / 5 = 4
        val time = "23:59:59"
        val expected = listOf(true, true, true, true)
        Assert.assertEquals(expected, berlinClock.getFiveHourRow(time))
    }

    @Test
    fun `getBerlinClockState should return correct state for a specific time`() {
        val time = "16:37:16" // Example time
        // Expected values based on our previous logic:
        // Seconds: 16 is even -> true
        // Five Hours: 16 / 5 = 3 -> [true, true, true, false]
        // Single Hours: 16 % 5 = 1 -> [true, false, false, false]
        // Five Minutes: 37 / 5 = 7 -> Y Y R Y Y R Y O O O O
        //      -> [YELLOW, YELLOW, RED, YELLOW, YELLOW, RED, YELLOW, OFF, OFF, OFF, OFF]
        // Single Minutes: 37 % 5 = 2 -> [true, true, false, false]

        val expectedState = BerlinClockState(
            secondsLamp = true,
            fiveHourRow = listOf(true, true, true, false),
            singleHourRow = listOf(true, false, false, false),
            fiveMinuteRow = listOf(
                LampState.YELLOW, LampState.YELLOW, LampState.RED,
                LampState.YELLOW, LampState.YELLOW, LampState.RED,
                LampState.YELLOW, LampState.OFF, LampState.OFF,
                LampState.OFF, LampState.OFF
            ),
            singleMinuteRow = listOf(true, true, false, false)
        )

        Assert.assertEquals(expectedState, berlinClock.getBerlinClockState(time))
    }

    @Test
    fun `getBerlinClockState should return correct state for midnight 00_00_00`() {
        val time = "00:00:00"
        val expectedState = BerlinClockState(
            secondsLamp = true, // Even second
            fiveHourRow = List(4) { false },
            singleHourRow = List(4) { false },
            fiveMinuteRow = List(11) { LampState.OFF },
            singleMinuteRow = List(4) { false }
        )
        Assert.assertEquals(expectedState, berlinClock.getBerlinClockState(time))
    }

    @Test
    fun `getBerlinClockState should return correct state for just before midnight 23_59_59`() {
        val time = "23:59:59"
        val expectedState = BerlinClockState(
            secondsLamp = false, // Odd second
            fiveHourRow = listOf(true, true, true, true), // 23/5 = 4
            singleHourRow = listOf(true, true, true, false), // 23%5 = 3
            fiveMinuteRow = listOf( // 59/5 = 11
                LampState.YELLOW, LampState.YELLOW, LampState.RED,
                LampState.YELLOW, LampState.YELLOW, LampState.RED,
                LampState.YELLOW, LampState.YELLOW, LampState.RED,
                LampState.YELLOW, LampState.YELLOW
            ),
            singleMinuteRow = listOf(true, true, true, true) // 59%5 = 4
        )
        Assert.assertEquals(expectedState, berlinClock.getBerlinClockState(time))
    }
}