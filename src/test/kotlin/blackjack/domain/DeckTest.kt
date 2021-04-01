package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class DeckTest {

    private lateinit var deck: Deck

    @BeforeEach
    fun setUp() {
        deck = Deck.newDeck()
    }

    @Test
    fun `카드를 구성하면 카드 갯수가 52장이 되어야 한다`() {
        assertThat(deck.cards.size).isEqualTo(52)
    }

    @Test
    fun `덱에서 카드 1장을 받으면 갯수가 줄어든다`() {
        deck.getCard()
        assertThat(deck.cards.size).isEqualTo(51)
    }

    @Test
    fun `게임의 기본 2장을 나눠준다`() {
        val cards = deck.getInitGameCards()
        assertThat(cards.size).isEqualTo(2)
    }
}
