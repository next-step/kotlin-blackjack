package fp

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

fun sumAll(numbers: List<Int>): Int {
    var total = 0
    for (number in numbers) {
        total += number
    }
    return total
}

fun sumAllEven(numbers: List<Int>): Int {
    var total = 0
    for (number in numbers) {
        if (number % 2 == 0) {
            total += number
        }
    }
    return total
}

fun sumAllOverThree(numbers: List<Int>): Int {
    var total = 0
    for (number in numbers) {
        if (number > 3) {
            total += number
        }
    }
    return total
}

class DuplicateRemoveTest {
    private val numbers: List<Int> = listOf(1, 2, 3, 4, 5, 6)

    @Test
    fun sumAll() {
        assertThat(sumAll(numbers)).isEqualTo(21)
    }

    @Test
    fun sumAllEven() {
        assertThat(sumAllEven(numbers)).isEqualTo(12)
    }

    @Test
    fun sumAllOverThree() {
        assertThat(sumAllOverThree(numbers)).isEqualTo(15)
    }
}
