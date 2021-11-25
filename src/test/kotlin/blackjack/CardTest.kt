package blackjack

import blackjack.domain.Card
import blackjack.domain.NumberType
import blackjack.domain.SuitType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class CardTest {

    @ParameterizedTest(name = "CardType과 NumberType으로 Card를 정의할 수 있다")
    @MethodSource("makeCardTest")
    fun `CardType과 NumberType으로 Card를 정의할 수 있다`(suitType: SuitType, numberType: NumberType, expected: Card) {
        val card = Card(suitType, numberType)

        assertThat(card).isEqualTo(expected)
    }

    @ParameterizedTest(name = "Card는 Ace를 가지고 있는지 알 수 있다")
    @MethodSource("hasAceTest")
    fun `Card는 Ace를 가지고 있는지 알 수 있다`(card: Card, expected: Boolean) {
        assertThat(card.hasAce()).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun makeCardTest(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(SuitType.DIAMOND, NumberType.KING, Card(SuitType.DIAMOND, NumberType.KING)),
                Arguments.of(SuitType.HEART, NumberType.JACK, Card(SuitType.HEART, NumberType.JACK)),
                Arguments.of(SuitType.SPADE, NumberType.ACE, Card(SuitType.SPADE, NumberType.ACE))
            )
        }

        @JvmStatic
        fun hasAceTest(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(Card(SuitType.DIAMOND, NumberType.KING), false),
                Arguments.of(Card(SuitType.HEART, NumberType.JACK), false),
                Arguments.of(Card(SuitType.SPADE, NumberType.ACE), true)
            )
        }
    }
}
