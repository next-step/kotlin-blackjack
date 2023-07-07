package domain.card

import java.util.Stack

class ShuffledCardDeck(cards: Cards) : CardDeck {
    private val cards = Stack<Card>()

    init {
        this.cards.addAll(cards.current().shuffled())
    }

    override fun pop(): Card {
        return cards.pop()
    }

    companion object {
        fun createNew(): ShuffledCardDeck {
            return ShuffledCardDeck(Cards.all())
        }
    }
}
