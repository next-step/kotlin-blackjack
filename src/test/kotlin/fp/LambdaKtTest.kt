package fp

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class LambdaKtTest {

    fun getNumbers(): Stream<Arguments> = Stream.of(Arguments.of(listOf(1, 2, 3, 4, 5, 6)))

    @ParameterizedTest
    @MethodSource("getNumbers")
    fun `sumAllTest`(numbers: List<Int>) {
        assertThat(sumAll(numbers)).isEqualTo(21)
    }

    @ParameterizedTest
    @MethodSource("getNumbers")
    fun `sumAllEvenTest`(numbers: List<Int>) {
        assertThat(sumAllEven(numbers)).isEqualTo(12)
    }

    @ParameterizedTest
    @MethodSource("getNumbers")
    fun `sumAllOverThreeTest`(numbers: List<Int>) {
        assertThat(sumAllOverThree(numbers)).isEqualTo(15)
    }
}
