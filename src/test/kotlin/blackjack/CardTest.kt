package blackjack

import domain.card.Card
import domain.card.CardType
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
                    Card.of(Denomination.ACE, CardType.SPADE), Denomination.ACE.numbers
                ),
                Arguments.of(
                    Card.of(Denomination.TWO, CardType.SPADE), Denomination.TWO.numbers
                ),
                Arguments.of(
                    Card.of(Denomination.THREE, CardType.SPADE), Denomination.THREE.numbers
                ),
                Arguments.of(
                    Card.of(Denomination.FOUR, CardType.SPADE), Denomination.FOUR.numbers
                ),
                Arguments.of(
                    Card.of(Denomination.FIVE, CardType.SPADE), Denomination.FIVE.numbers
                ),
                Arguments.of(
                    Card.of(Denomination.SIX, CardType.SPADE), Denomination.SIX.numbers
                ),
                Arguments.of(
                    Card.of(Denomination.SEVEN, CardType.SPADE), Denomination.SEVEN.numbers
                ),
                Arguments.of(
                    Card.of(Denomination.EIGHT, CardType.SPADE), Denomination.EIGHT.numbers
                ),
                Arguments.of(
                    Card.of(Denomination.NINE, CardType.SPADE), Denomination.NINE.numbers
                ),
                Arguments.of(
                    Card.of(Denomination.TEN, CardType.SPADE), Denomination.TEN.numbers
                ),
                Arguments.of(
                    Card.of(Denomination.KING, CardType.SPADE), Denomination.KING.numbers
                ),
                Arguments.of(
                    Card.of(Denomination.QUEEN, CardType.SPADE), Denomination.QUEEN.numbers
                ),
                Arguments.of(
                    Card.of(Denomination.JACK, CardType.SPADE), Denomination.JACK.numbers
                ),
            )
        }
    }
}
