package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.game.Deck
import blackjack.domain.game.Game

class Dealer : Participant() {
    private val deck: Deck = shuffleNewDeck()

    override fun isDrawable(): Boolean {
        return hand.score() <= DEALER_DRAWABLE_SCORE_LIMIT
    }

    fun drawCards(num: Int): List<Card> {
        val cards = mutableListOf<Card>()
        repeat(num) {
            cards.add(deck.draw())
        }
        return cards.toList()
    }

    fun drawOneCard(): Card {
        return deck.draw()
    }

    private fun shuffleNewDeck(): Deck {
        return STANDARD_52_CARD_DECK.shuffle()
    }

    companion object {
        private val STANDARD_52_CARD_DECK: Deck = Deck(Game.ALL_CARDS)
        private const val DEALER_DRAWABLE_SCORE_LIMIT = 16
    }
}
