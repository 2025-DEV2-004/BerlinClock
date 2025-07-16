package com.exercise.berlinclock

import com.exercise.berlinclock.model.BerlinClock
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before


class BerlinClockTest {

    private lateinit var berlinClock: BerlinClock

    @Before
    fun setup() {
        berlinClock = BerlinClock()
    }

    @Test
    fun `secondsLamp should be Y for even seconds`() {
        val time = "00:00:00" // Even second
        assertEquals("Y", berlinClock.getSecondsLamp(time))
    }

    @Test
    fun `secondsLamp should be O for odd seconds`() {
        val time = "00:00:01" // Odd second
        assertEquals("O", berlinClock.getSecondsLamp(time))
    }
}