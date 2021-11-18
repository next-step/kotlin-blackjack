package domain.card

data class PlayingCard private constructor(val suit: Suit, val denomination: Denomination) {
    fun score(accumulatedScore: Int) =
        if (denomination == Denomination.ACE
            && accumulatedScore + ACE_ALTERNATIVE <= BLACKJACK_NUMBER
        ) ACE_ALTERNATIVE
        else denomination.score

    companion object {
        fun of(suit: Suit, denomination: Denomination) = cards[suit]!![denomination]!!
        fun createMutableList() = cards.values.flatMap { it.values }.toMutableList()
        const val BLACKJACK_NUMBER = 21
        private const val ACE_ALTERNATIVE = 11
        private val cards = Suit.values().associateWith { createCards(it) }
        private fun createCards(suit: Suit) = Denomination.values().associateWith { PlayingCard(suit, it) }
    }
}
