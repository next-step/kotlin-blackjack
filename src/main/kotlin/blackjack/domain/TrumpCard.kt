package blackjack.domain

@JvmInline
value class TrumpCard(val cards: Cards) {
    val size: Int
        get() = cards.cards.size
    private val firstCard: Card
        get() = cards.cards.first()

    fun draw(): Card {
        return firstCard.also {
            cards.remove(it)
        }
    }

    companion object {
        fun init(): TrumpCard {
            val cardSet = mutableSetOf<Card>()
            Suit.values().forEach { suit ->
                cardSet.addAll(Rank.values().map { Card(suit, it) })
            }
            cardSet.shuffled()
            return TrumpCard(Cards(cardSet))
        }
    }
}
