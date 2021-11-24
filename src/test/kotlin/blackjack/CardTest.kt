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
    fun `CardType과 NumberType으로 Card를 정의할 수 있다`(value1: SuitType, value2: NumberType, expected: Card) {
        val card = Card(value1, value2)

        assertThat(card).isEqualTo(expected)
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
    }
}
