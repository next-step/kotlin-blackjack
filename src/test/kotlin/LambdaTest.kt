import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LambdaTest {
    private val numbers: List<Int> = listOf(1, 2, 3, 4, 5, 6)
    val conditionAll: (item: Int) -> Boolean = { true }
    val conditionOdd: (item: Int) -> Boolean = { item -> item % 2 == 0 }
    val conditionOverThree: (item: Int) -> Boolean = { item -> item > 3 }
    private fun List<Int>.sumBy(condition: (item: Int) -> Boolean) = this.filter(condition).sum()

    fun sumAll(numbers: List<Int>): Int {
        return numbers.sumBy { conditionAll(it) }
    }

    fun sumAllEven(numbers: List<Int>): Int {
        return numbers.sumBy { conditionOdd(it) }
    }

    fun sumAllOverThree(numbers: List<Int>): Int {
        return numbers.sumBy { conditionOverThree(it) }
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
