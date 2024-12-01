package blackjack.domain

class Cards private constructor(
    val cards: MutableSet<Card> = mutableSetOf()
) {
    fun add(card: Card) {
        cards.add(card)
    }

    fun draw(): Card {
        val card = cards.first()
        cards.remove(card)
        return card
    }

    private fun setup() {
        Suit.entries.forEach(::addCardsForSuit)
    }

    private fun addCardsForSuit(suit: Suit) {
        Rank.entries.forEach { rank ->
            cards.add(Card(suit, rank))
        }
    }

    companion object {
        fun fullCards(): Cards {
            val cards = Cards()
            cards.setup()
            return cards
        }

        fun emptyCards(): Cards {
            return Cards()
        }
    }
}
