package blackjack

import domain.card.Card
import domain.card.Denomination
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class CardTest {
    @ParameterizedTest
    @MethodSource("카드 숫자 테스트 데이터")
    fun `카드 숫자 테스트`(card: Card, numbers: Set<Int>) {
        assertThat(card.numbers).isEqualTo(numbers)
    }

    companion object {
        @JvmStatic
        fun `카드 숫자 테스트 데이터`(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    spadeAce, Denomination.ACE.numbers
                ),
                Arguments.of(
                    spadeTwo, Denomination.TWO.numbers
                ),
                Arguments.of(
                    spadeThree, Denomination.THREE.numbers
                ),
                Arguments.of(
                    spadeFour, Denomination.FOUR.numbers
                ),
                Arguments.of(
                    spadeFive, Denomination.FIVE.numbers
                ),
                Arguments.of(
                    spadeSix, Denomination.SIX.numbers
                ),
                Arguments.of(
                    spadeSeven, Denomination.SEVEN.numbers
                ),
                Arguments.of(
                    spadeEight, Denomination.EIGHT.numbers
                ),
                Arguments.of(
                    spadeNine, Denomination.NINE.numbers
                ),
                Arguments.of(
                    spadeTen, Denomination.TEN.numbers
                ),
                Arguments.of(
                    spadeKing, Denomination.KING.numbers
                ),
                Arguments.of(
                    spadeQueen, Denomination.QUEEN.numbers
                ),
                Arguments.of(
                    spadeJack, Denomination.JACK.numbers
                ),
            )
        }
    }
}
