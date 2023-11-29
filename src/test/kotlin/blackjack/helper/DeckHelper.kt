package blackjack.helper

import blackjack.domain.card.Card
import blackjack.domain.card.CardShuffler
import blackjack.domain.Deck

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
