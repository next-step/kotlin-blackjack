package balckjac.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class DeckTest {

    @Test
    fun `Deck 은 52개의 카드로 구성되어야 한다`() {
        val deck = Deck(cards = (0..51).toList().map { Card(Suit.DIA, Denomination.FIVE) })
        assertThat(deck.cards.size).isEqualTo(52)
    }

    @Test
    fun `Deck 은 52개가 아닌 경우 Exception 확인`() {
        assertThrows<IllegalArgumentException> {
            Deck(cards = (0..10).toList().map { Card(Suit.DIA, Denomination.FIVE) })
        }
        assertThrows<IllegalArgumentException> {
            Deck(cards = (0..53).toList().map { Card(Suit.DIA, Denomination.FIVE) })
        }
    }

    @Test
    fun `Deck 은 카드를 분배할 수 있으며 분배된 카드는 Deck 에서 제거된다`() {
        val deck = Deck(cards = (0..51).toList().map { Card(Suit.DIA, Denomination.FIVE) })
        deck.draw()
        assertThat(deck.cards.size).isEqualTo(51)
    }
}
