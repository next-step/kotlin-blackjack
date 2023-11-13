package blackjack.domain

class TrumpCard(cards: Cards) {
    private val cards = Cards(cards)
    val size: Int
        get() = cards.cards.size
    private val firstCard: Card
        get() = cards.cards.first()

    fun draw(): Card {
        return firstCard.also {
            cards.remove(it)
        }
    }

    fun drawFirstCards(): Cards {
        return Cards(setOf(draw(), draw()))
    }

    companion object {
        fun init(): TrumpCard {
            val cards = Suit.values().flatMap { suit ->
                Rank.values().map { Card(suit, it) }
            }.shuffled()
            return TrumpCard(Cards(cards.toMutableSet()))
        }
    }
}
