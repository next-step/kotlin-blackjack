package blackjack.domain

class TrumpCard(cards: Cards) {
    private val _cards = cards.cards.safeCopy()
    private val cards = Cards(_cards.safeCopy())
    val size: Int
        get() = cards.cards.size
    private val firstCard: Card
        get() = cards.cards.first()

    fun draw(): Card {
        return firstCard.also {
            cards.remove(it)
        }
    }

    fun firstCardDraw(): Cards {
        return Cards(setOf(draw(), draw()))
    }

    private fun MutableSet<Card>.safeCopy() = this.map { it.copy() }.toMutableSet()

    companion object {
        fun init(): TrumpCard {
            val cards = Suit.values().flatMap { suit ->
                Rank.values().map { Card(suit, it) }
            }.shuffled()
            return TrumpCard(Cards(cards.toMutableSet()))
        }
    }
}
