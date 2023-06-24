package blackjack.model

class HandDeck(cards: Collection<TrumpCard> = emptyList()) {

    val cards: List<TrumpCard> = cards.toList()

    operator fun plus(card: TrumpCard): HandDeck {
        return HandDeck(cards + card)
    }

    val aceCount: Int
        get() {
            return cards.count { it.number == TrumpCardNumber.ACE }
        }

    fun sumOf(selector: (TrumpCard) -> Int): Int {
        return cards.sumOf { selector(it) }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HandDeck

        return cards == other.cards
    }

    override fun hashCode(): Int {
        return cards.hashCode()
    }
}
