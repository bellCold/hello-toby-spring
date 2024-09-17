package tobyspring.hellospring.learningtest

import org.assertj.core.api.Assertions.assertThat
import java.time.Clock
import java.time.Instant
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import kotlin.test.Test

class ClockTest {

    @Test
    fun clock() {
        val clock = Clock.systemDefaultZone()

        val dt1 = LocalTime.now(clock)
        val dt2 = LocalTime.now(clock)

        assertThat(dt2).isAfter(dt1)
    }

    @Test
    fun fixedClock() {
        val clock = Clock.fixed(Instant.now(), ZoneId.systemDefault())

        val dt1 = LocalDateTime.now(clock)
        val dt2 = LocalDateTime.now(clock)
        val dt3 = LocalDateTime.now(clock).plusHours(1)

        assertThat(dt1).isEqualTo(dt2)
        assertThat(dt3).isEqualTo(dt2.plusHours(1))
    }
}