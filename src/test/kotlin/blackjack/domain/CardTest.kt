package blackjack.domain

import blackjack.model.Card
import blackjack.model.CardShape
import blackjack.model.CardType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class CardTest {

    @ParameterizedTest
    @MethodSource("provideValidCard")
    fun `각 카드 타입 별로 유효한 값(value)과 점수 및 모양을 생성할 수 있다`(cardType: CardType, cardShape: CardShape) {
        val card = Card(cardType, cardShape)
        assertThat(card.type).isEqualTo(cardType)
        assertThat(card.shape).isEqualTo(cardShape)
    }

    companion object {
        @JvmStatic
        fun provideValidCard(): Stream<Arguments> =
            Stream.of(
                Arguments.of(CardType.ACE, CardShape.CLOVER),
                Arguments.of(CardType.NINE, CardShape.SPADE),
                Arguments.of(CardType.JACK, CardShape.HEART)
            )
    }
}
