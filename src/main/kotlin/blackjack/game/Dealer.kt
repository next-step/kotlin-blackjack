package blackjack.game

import blackjack.card.Card

class Dealer {

    private val deck: Deck = shuffleNewDeck()

    fun drawCard(): Card {
        return deck.draw()
    }

    fun getNumOfRemainCards(): Int {
        return deck.size()
    }

    private fun shuffleNewDeck(): Deck {
        return STANDARD_52_CARD_DECK.shuffle()
    }

    companion object {
        val STANDARD_52_CARD_DECK: Deck = Deck(ALL_CARDS)
    }
}
