package domain.card

import java.util.Stack

class ShuffledCardDeck(cards: List<Card>) : CardDeck {
    private val cards = Stack<Card>()

    init {
        this.cards.addAll(cards.shuffled())
    }

    override fun pop(): Card {
        return cards.pop()
    }

    companion object {
        fun createNew(): ShuffledCardDeck {
            return ShuffledCardDeck(CardDeck.createNewDeck())
        }
    }
}
