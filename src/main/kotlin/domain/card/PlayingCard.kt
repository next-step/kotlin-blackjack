package domain.card

data class PlayingCard private constructor(val denomination: Denomination, val suit: Suit) {
    fun score(accumulatedScore: Int) =
        if (denomination == Denomination.ACE
            && accumulatedScore + ACE_ALTERNATIVE <= BLACKJACK_NUMBER
        ) ACE_ALTERNATIVE
        else denomination.score

    companion object {
        fun of(denomination: Denomination, suit: Suit) = cards[denomination]!![suit]!!
        fun createMutableList() = cards.values.flatMap { it.values }.toMutableList()
        const val BLACKJACK_NUMBER = 21
        private const val ACE_ALTERNATIVE = 11
        private val cards = Denomination.values().associateWith { createCards(it) }
        private fun createCards(denomination: Denomination) =
            Suit.values().associateWith { PlayingCard(denomination, it) }
    }
}
