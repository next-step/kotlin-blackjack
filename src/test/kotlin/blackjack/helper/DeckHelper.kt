package blackjack.helper

import blackjack.domain.Deck
import blackjack.domain.card.Card
import blackjack.domain.card.CardShuffler

object DeckHelper {

    fun createMockDeck(): Deck {
        return Deck(shuffler = createShuffler())
    }

    private fun createShuffler(): CardShuffler {
        return object : CardShuffler {
            override fun shuffle(cards: List<Card>): List<Card> {
                return cards
            }
        }
    }
}
