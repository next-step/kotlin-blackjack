package blackjack.domain

import blackjack.model.Card
import blackjack.model.CardShape
import blackjack.model.CardType
import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class CardTest {

    @ParameterizedTest
    @MethodSource("provideValidCard")
    fun `각 카드 타입 별로 유효한 값(value)과 점수 및 모양을 가지고 있다`(card: Card, value: String, score: Int, shape: String) {
        Assertions.assertThat(card.type.value).isEqualTo(value)
        Assertions.assertThat(card.type.score).isEqualTo(score)
        Assertions.assertThat(card.shape.text).isEqualTo(shape)
    }

    companion object {
        @JvmStatic
        fun provideValidCard(): Stream<Arguments> =
            Stream.of(
                Arguments.of(Card(CardType.ACE, CardShape.CLOVER), "A", 1, "클로버"),
                Arguments.of(Card(CardType.NINE, CardShape.SPADE), "9", 9, "스페이드"),
                Arguments.of(Card(CardType.JACK, CardShape.HEART), "J", 10, "하트")
            )
    }
}
