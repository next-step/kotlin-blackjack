package blackjack.domain

import blackjack.model.DEFAULT_CARD_DECK
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class CardDeckTest {
    @ParameterizedTest
    @ValueSource(ints = [52])
    fun `카드 모양, 종류를 조합하여 총 52장의 카드(덱) 생성할 수 있다`(cardDeckCount: Int) {
        val cardDeck = CardDeck()
        assertThat(cardDeck.cards.size).isEqualTo(cardDeckCount)
    }

    @Test
    fun `카드 덱을 셔플 할 수 있다`() {
        val defaultCards = DEFAULT_CARD_DECK
        val shuffledCards = defaultCards.shuffled()
        val cardDeck = FakeCardDeck(shuffledCards)
        assertThat(cardDeck.cards).isEqualTo(shuffledCards)
    }
}
