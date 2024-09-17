package tobyspring.hellospring

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SortTest {
    private lateinit var sort: Sort

    @BeforeEach
    fun setUp() {
        sort = Sort()
    }

    @Test
    fun sort() {
        val list = sort.sortByLength(mutableListOf("1", "123"))

        assertThat(list).isEqualTo(listOf("1", "123"))
    }

    @Test
    fun sort3Items() {
        val list = sort.sortByLength(mutableListOf("1", "123", "12"))

        assertThat(list).isEqualTo(listOf("1", "12", "123"))
    }

    @Test
    fun alreadySort() {
        val list = sort.sortByLength(mutableListOf("1", "12", "123"))

        assertThat(list).isEqualTo(listOf("1", "12", "123"))
    }
}