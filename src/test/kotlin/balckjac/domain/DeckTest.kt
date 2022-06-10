package balckjac.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DeckTest {

    @Test
    fun `Deck 은 52개의 카드로 구성되어야 한다`() {
        val deck = Deck(cards = (0..51).toList().map { Card(Suit.DIA, Denomination.FIVE) })
        assertThat(deck.cards.size).isEqualTo(52)
    }
}
