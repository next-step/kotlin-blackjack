package domain.card

import java.util.Stack

class CardDeck {
    private val cards = Stack<Card>()

    init {
        createNewDeck().shuffled()
            .forEach {
                cards.push(it)
            }
    }

    fun pop(): Card? {
        if (cards.isEmpty()) return null
        return cards.pop()
    }

    private fun createNewDeck(): List<Card> {
        return Spade.createDeck()
            .plus(Heart.createDeck())
            .plus(Diamond.createDeck())
            .plus(Clover.createDeck())
    }
}
