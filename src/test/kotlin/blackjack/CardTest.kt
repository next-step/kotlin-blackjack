package blackjack

import blackjack.domain.Card
import blackjack.domain.Denomination
import blackjack.domain.SuitType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class CardTest {

    @ParameterizedTest(name = "CardType과 NumberType으로 Card를 정의할 수 있다")
    @MethodSource("makeCardTest")
    fun `CardType과 NumberType으로 Card를 정의할 수 있다`(suitType: SuitType, denomination: Denomination, expected: Card) {
        val card = Card(suitType, denomination)

        assertThat(card).isEqualTo(expected)
    }

    @ParameterizedTest(name = "Card는 Ace를 가지고 있는지 알 수 있다")
    @MethodSource("isAceTest")
    fun `Card는 Ace인지 알 수 있다`(card: Card, expected: Boolean) {
        assertThat(card.isAce()).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun makeCardTest(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(SuitType.DIAMOND, Denomination.KING, Card(SuitType.DIAMOND, Denomination.KING)),
                Arguments.of(SuitType.HEART, Denomination.JACK, Card(SuitType.HEART, Denomination.JACK)),
                Arguments.of(SuitType.SPADE, Denomination.ACE, Card(SuitType.SPADE, Denomination.ACE))
            )
        }

        @JvmStatic
        fun isAceTest(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(Card(SuitType.DIAMOND, Denomination.KING), false),
                Arguments.of(Card(SuitType.HEART, Denomination.JACK), false),
                Arguments.of(Card(SuitType.SPADE, Denomination.ACE), true)
            )
        }
    }
}
