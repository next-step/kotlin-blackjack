package blackjack.domain

import blackjack.model.CardShape
import blackjack.model.CardType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import java.util.stream.Stream

internal class CardsTest {

    @DisplayName("카드 모양, 종류를 조합하여 총 52장의 카드(덱) 생성할 수 있다.")
    @ParameterizedTest
    @ValueSource(ints = [52])
    fun cardDeck(cardDeckCount: Int) {
        assertThat(Cards(DEFAULT_CARD_DECK).value.size).isEqualTo(cardDeckCount)
    }

    @DisplayName("카드 목록에 카드를 추가할 수 있다.")
    @ParameterizedTest
    @MethodSource("provideCard")
    fun addCard(card: Card) {
        val cards = Cards().apply { add(card) }
        assertThat(cards.value.first()).isEqualTo(card)
    }

    companion object {
        @JvmStatic
        fun provideCard(): Stream<Card> =
            Stream.of(Card(CardType.JACK, CardShape.CLOVER), Card(CardType.ACE, CardShape.CLOVER))
    }
}
