package blackjack.helper

import blackjack.domain.Deck
import blackjack.domain.card.Card
import blackjack.domain.card.ShuffleMachine

object DeckHelper {

    fun createMockDeck(): Deck {
        return Deck(shuffleMachine = createShuffleMachine())
    }

    private fun createShuffleMachine(): ShuffleMachine {
        return object : ShuffleMachine {
            override fun shuffle(cards: List<Card>): List<Card> {
                return cards
            }
        }
    }
}
