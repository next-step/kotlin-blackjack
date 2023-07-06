package blackjack.domain

import blackjack.domain.Hands.Companion.INIT_CARD_SIZE

class CardDeck {
    private var index = FIRST_CARD_INDEX
    private var deck: List<Card> = Card.CARD_DECK.values.toList()

    fun firstDraw(): List<Card> {
        return List(INIT_CARD_SIZE) { draw() }
    }

    fun draw(): Card {
        if (index == deck.size) shuffle()

        return deck[index++]
    }

    fun shuffle(): CardDeck {
        deck = deck.shuffled()
        index = FIRST_CARD_INDEX
        return this
    }

    companion object {
        private const val FIRST_CARD_INDEX = 0
    }
}