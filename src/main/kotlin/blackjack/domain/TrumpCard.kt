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

    private fun MutableSet<Card>.safeCopy() = this.map { it.copy() }.toMutableSet()

    companion object {
        fun init(): TrumpCard {
            val cardSet = mutableListOf<Card>()
            Suit.values().forEach { suit ->
                cardSet.addAll(Rank.values().map { Card(suit, it) })
            }
            cardSet.shuffle()
            return TrumpCard(Cards(cardSet.toMutableSet()))
        }
    }
}
