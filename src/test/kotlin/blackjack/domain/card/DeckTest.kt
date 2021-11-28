package blackjack.domain.card

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@Suppress("NonAsciiCharacters")
class DeckTest {

    @Test
    fun `Deck 생성`() {
        val deck = Deck.create()

        assertThat(deck.cards.size).isEqualTo(52)
    }

    @Test
    fun `Deck에서 카드를 뽑는다`() {
        val deck = Deck(
            listOf(
                Card(Symbol.ACE, Type.CLUB),
                Card(Symbol.ACE, Type.DIAMOND),
            )
        )

        val card = deck.drawCard()

        assertThat(deck.cards.size).isEqualTo(1)
        assertThat(card).isEqualTo(Card(Symbol.ACE, Type.CLUB))
    }

    @Test
    fun `Deck에서 카드가 없다면 뽑지 못한다`() {
        val deck = Deck(emptyList())

        assertThrows<IllegalStateException> { deck.drawCard() }
    }
}
