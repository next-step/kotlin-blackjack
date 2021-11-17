package domain.card

data class PlayingCard private constructor(val suit: Suit, val denomination: Denomination) {
    fun score(accumulatedScore: Int) =
        if (denomination == Denomination.ACE && accumulatedScore <= ACE_THRESHOLD)
            ACE_ALTERNATIVE
        else denomination.score

    companion object {
        fun of(suit: Suit, denomination: Denomination) = cards[suit]!![denomination]!!
        fun createMutableList() = cards.values.flatMap { it.values }.toMutableList()
        private const val ACE_THRESHOLD = 11
        private const val ACE_ALTERNATIVE = 10
        private val cards = Suit.values().associateWith { createCards(it) }
        private fun createCards(suit: Suit) = Denomination.values().associateWith { PlayingCard(suit, it) }
    }
}
