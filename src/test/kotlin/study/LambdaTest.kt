package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LambdaTest {
    private val numbers: List<Int> = listOf(1, 2, 3, 4, 5, 6)

    @Test
    fun sumAll() {
        assertThat(sum(numbers) { true }).isEqualTo(sumAll(numbers))
    }

    @Test
    fun sumAllEven() {
        assertThat(sum(numbers) { it % 2 == 0 }).isEqualTo(sumAllEven(numbers))
    }

    @Test
    fun sumAllOverThree() {
        assertThat(sum(numbers) { it > 3 }).isEqualTo(sumAllOverThree(numbers))
    }
}

fun sum(numbers: List<Int>, condition: (Int) -> Boolean): Int {
    var total = 0
    for (number in numbers) {
        if (condition(number)) {
            total += number
        }
    }
    return total
}

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
