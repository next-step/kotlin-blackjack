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
                    Card.spade(Denomination.ACE), Denomination.ACE.numbers
                ),
                Arguments.of(
                    Card.spade(Denomination.TWO), Denomination.TWO.numbers
                ),
                Arguments.of(
                    Card.spade(Denomination.THREE), Denomination.THREE.numbers
                ),
                Arguments.of(
                    Card.spade(Denomination.FOUR), Denomination.FOUR.numbers
                ),
                Arguments.of(
                    Card.spade(Denomination.FIVE), Denomination.FIVE.numbers
                ),
                Arguments.of(
                    Card.spade(Denomination.SIX), Denomination.SIX.numbers
                ),
                Arguments.of(
                    Card.spade(Denomination.SEVEN), Denomination.SEVEN.numbers
                ),
                Arguments.of(
                    Card.spade(Denomination.EIGHT), Denomination.EIGHT.numbers
                ),
                Arguments.of(
                    Card.spade(Denomination.NINE), Denomination.NINE.numbers
                ),
                Arguments.of(
                    Card.spade(Denomination.TEN), Denomination.TEN.numbers
                ),
                Arguments.of(
                    Card.spade(Denomination.KING), Denomination.KING.numbers
                ),
                Arguments.of(
                    Card.spade(Denomination.QUEEN), Denomination.QUEEN.numbers
                ),
                Arguments.of(
                    Card.spade(Denomination.JACK), Denomination.JACK.numbers
                ),
            )
        }
    }
}
