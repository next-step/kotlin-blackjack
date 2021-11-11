package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class CardDeckTest {
    @Test
    fun `덱은 총 52장의 카드를 가진다`() {
        val cardDeck = CardDeck()

        assertThat(cardDeck.cards.size).isEqualTo(52)
    }

    @ParameterizedTest
    @EnumSource(CardSymbol::class)
    fun `덱은 문양별로 1에서 13번 까지의 카드를 가진다`(symbol: CardSymbol) {
        val cardDeck = CardDeck()

        assertThat(cardDeck.cards).containsSequence((1..13).map { Card(symbol, CardNumber(it)) })
    }
}
