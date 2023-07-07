package blackjack

import domain.card.Denomination
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class DenominationTest {
    @Test
    fun `denomination에는 ace, 2, 3, 4, 5, 6, 7, 8, 9, 10, king, queen, jack이 있어야 한다`() {
        val denominations = mutableSetOf(
            Denomination.ACE,
            Denomination.TWO,
            Denomination.THREE,
            Denomination.FOUR,
            Denomination.FIVE,
            Denomination.SIX,
            Denomination.SEVEN,
            Denomination.EIGHT,
            Denomination.NINE,
            Denomination.TEN,
            Denomination.KING,
            Denomination.QUEEN,
            Denomination.JACK,
        )

        assertThat(denominations.size == Denomination.values().size).isTrue()
        assertThat(denominations).containsAll(Denomination.values().toList())
    }

    @ParameterizedTest
    @MethodSource("denomination 숫자 테스트 데이터")
    fun `denomination이 갖는 숫자 테스트`(denomination: Denomination, numbers: Set<Int>) {
        assertThat(denomination.numbers).isEqualTo(numbers)
    }

    companion object {
        @JvmStatic
        fun `denomination 숫자 테스트 데이터`(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    Denomination.ACE, setOf(1, 11)
                ),
                Arguments.of(
                    Denomination.TWO, setOf(2)
                ),
                Arguments.of(
                    Denomination.THREE, setOf(3)
                ),
                Arguments.of(
                    Denomination.FOUR, setOf(4)
                ),
                Arguments.of(
                    Denomination.FIVE, setOf(5)
                ),
                Arguments.of(
                    Denomination.SIX, setOf(6)
                ),
                Arguments.of(
                    Denomination.SEVEN, setOf(7)
                ),
                Arguments.of(
                    Denomination.EIGHT, setOf(8)
                ),
                Arguments.of(
                    Denomination.NINE, setOf(9)
                ),
                Arguments.of(
                    Denomination.TEN, setOf(10)
                ),
                Arguments.of(
                    Denomination.KING, setOf(10)
                ),
                Arguments.of(
                    Denomination.QUEEN, setOf(10)
                ),
                Arguments.of(
                    Denomination.JACK, setOf(10)
                ),
            )
        }
    }
}
