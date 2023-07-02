package blackjack

import domain.card.Card
import domain.card.Cards
import domain.card.Denomination
import domain.card.Diamond
import domain.card.Spade
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class CardsTest {
    @ParameterizedTest
    @MethodSource("카드 더 받을 수 있는지 확인 테스트 데이터")
    fun `갖고있는 카드에서 결과 계산 테스트`(cards: Set<Card>, result: Int) {
        with(Cards()) {
            cards.forEach {
                add(it)
            }
            assertThat(result()).isEqualTo(result)
        }
    }

    companion object {
        @JvmStatic
        fun `카드 더 받을 수 있는지 확인 테스트 데이터`(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    setOf(Spade.get(Denomination.TWO), Spade.get(Denomination.THREE)), 5
                ),
                Arguments.of(
                    setOf(Spade.get(Denomination.ACE), Diamond.get(Denomination.ACE)), 2
                ),
                Arguments.of(
                    setOf(Spade.get(Denomination.ACE), Spade.get(Denomination.KING)), 21
                ),
                Arguments.of(
                    setOf(Spade.get(Denomination.ACE), Spade.get(Denomination.NINE), Diamond.get(Denomination.ACE)),
                    21
                ),
                Arguments.of(
                    setOf(Spade.get(Denomination.TWO), Spade.get(Denomination.TEN), Spade.get(Denomination.KING)), 22
                ),
            )
        }
    }
}
