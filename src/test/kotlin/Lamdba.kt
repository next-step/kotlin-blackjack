import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Lambda {
    val numbers: List<Int> = listOf(1, 2, 3, 4, 5, 6)
    val conditionOdd: (item: Int) -> Boolean = { item -> item % 2 == 0 }
    val conditionOverThree: (item: Int) -> Boolean = { item -> item > 3 }
    fun sumAll(numbers: List<Int>): Int {
        return numbers.sum()
    }

    fun sumAllEven(numbers: List<Int>): Int {
        return numbers.filter { conditionOdd(it) }.sum()
    }

    fun sumAllOverThree(numbers: List<Int>): Int {
        return numbers.filter { conditionOverThree(it) }.sum()
    }

    @Test
    fun `sumAllTest`() {
        assertThat(sumAll(numbers)).isEqualTo(1 + 2 + 3 + 4 + 5 + 6)
    }

    @Test
    fun `sumAllEvenTest`() {
        assertThat(sumAllEven(numbers)).isEqualTo(2 + 4 + 6)
    }

    @Test
    fun `sumAllOverThreeTest`() {
        assertThat(sumAllOverThree(numbers)).isEqualTo(4 + 5 + 6)
    }
}
