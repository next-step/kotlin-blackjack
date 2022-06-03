package blackjack.game

import blackjack.card.Card
import blackjack.util.ALL_CARDS

class Dealer {

    private val deck: Deck = shuffleNewDeck()

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
