package blackjack

import domain.card.Card
import domain.card.Cards
import domain.card.Denomination
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class CardsTest {
    @ParameterizedTest
    @MethodSource("카드 더 받을 수 있는지 확인 테스트 데이터")
    fun `갖고있는 카드에서 결과 계산 테스트`(cards: List<Card>, result: Int) {
        assertThat(Cards(cards).result()).isEqualTo(result)
    }

    companion object {
        @JvmStatic
        fun `카드 더 받을 수 있는지 확인 테스트 데이터`(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    listOf(Card.spade(Denomination.TWO), Card.spade(Denomination.THREE)), 5
                ),

                Arguments.of(
                    listOf(Card.spade(Denomination.ACE), Card.diamond(Denomination.ACE)), 2
                ),

                Arguments.of(
                    listOf(Card.spade(Denomination.ACE), Card.spade(Denomination.KING)), 21
                ),

                Arguments.of(
                    listOf(Card.spade(Denomination.ACE), Card.spade(Denomination.NINE), Card.diamond(Denomination.ACE)),
                    21
                ),

                Arguments.of(
                    listOf(Card.spade(Denomination.TWO), Card.spade(Denomination.TEN), Card.spade(Denomination.KING)),
                    22
                ),
            )
        }
    }
}
