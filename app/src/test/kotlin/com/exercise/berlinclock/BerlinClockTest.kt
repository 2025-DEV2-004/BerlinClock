package com.exercise.berlinclock

import com.exercise.berlinclock.model.BerlinClock
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
    fun `secondsLamp should be Y for even seconds`() {
        val time = "00:00:00" // Even second
        assertTrue(berlinClock.getSecondsLamp(time))
    }

    @Test
    fun `secondsLamp should be O for odd seconds`() {
        val time = "00:00:01" // Odd second
        assertFalse(berlinClock.getSecondsLamp(time))
    }
}