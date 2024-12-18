package blackjack.domain.card

import java.util.LinkedList

class CardDeck {
    private val cards: LinkedList<BlackJackCard> by lazy {
        GeneratorCards().generateCardDeck()
    }

    fun getNextCard(): BlackJackCard {
        return cards.pop()
    }

    fun remainCount() = cards.size
}
