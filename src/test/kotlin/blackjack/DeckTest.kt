package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.LinkedList
import java.util.Queue

class DeckTest {

    @Test
    fun `총 카드 개수 확인`() {
        assertThat(Deck.cards.size).isEqualTo(Deck.TOTAL_DECK_SIZE)
    }
}

object Deck {
    const val TOTAL_DECK_SIZE = 52

    val cards: Queue<Card> = LinkedList<Card>(
        accumulateCards()
            .flatten()
            .shuffled()
    )

    private fun accumulateCards(): List<List<Card>> =
        Denomination.values()
            .map(::setupCard)

    private fun setupCard(denomination: Denomination): List<Card> =
        Shape.values().map { shape ->
            Card(denomination to shape)
        }
}
