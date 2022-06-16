package blackjack.domain

import blackjack.domain.Deck.Companion.CARD_LIST
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DeckTest {

    @Test
    fun `Deck 은 52개의 카드로 구성되어야 한다`() {
        val deck = Deck(CARD_LIST)
        assertThat(deck.cards.size).isEqualTo(52)
    }

    @Test
    fun `Deck 은 52개가 아닌 경우 Exception 확인`() {
        assertThrows<IllegalArgumentException> { Deck(cards = CARD_LIST.take(10).toSet()) }
    }

    @Test
    fun `Deck 은 카드를 분배할 수 있으며 분배된 카드는 Deck 에서 제거된다`() {
        val deck = Deck(CARD_LIST)
        deck.draw()
        assertThat(deck.cards.size).isEqualTo(51)
    }

    @Test
    fun `자동 생성되는 Deck 은 52개의 카드로 구성되어야 한다`() {
        val deck = Deck()
        assertThat(deck.cards.size).isEqualTo(52)
    }
}
