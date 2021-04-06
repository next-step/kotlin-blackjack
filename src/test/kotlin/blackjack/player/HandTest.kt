package blackjack.player

import blackjack.playingcard.Card
import blackjack.playingcard.Cards
import blackjack.playingcard.Suit
import blackjack.playingcard.Symbol
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class HandTest {
    @Test
    fun `손패는 카드리스트를 가진다`() {
        assertDoesNotThrow { Hand(cards = Cards()) }
    }

    @ParameterizedTest
    @CsvSource(
        "SPADES, ACE",
        "HEARTS, SEVEN"
    )
    fun `손패에 카드를 주어 카드를 추가할 수 있다`(suit: Suit, symbol: Symbol) {
        // given
        val givenCard = Card(Suit.DIAMONDS, Symbol.NINE)
        val hand = Hand(Cards(listOf(givenCard)))

        val newCard = Card(suit, symbol)
        val expected = listOf(givenCard, newCard)

        // when
        hand.add(newCard)
        val actual = hand.cards.toList()

        // then
        assertThat(actual).containsExactlyElementsOf(expected)
    }
}
