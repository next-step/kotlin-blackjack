package domain.card

data class PlayingCard private constructor(val denomination: Denomination, val suit: Suit) {
    fun score(): Int = denomination.score

    companion object {
        fun of(denomination: Denomination, suit: Suit) = cards[denomination]!![suit]!!
        fun createMutableList() = cards.values.flatMap { it.values }.toMutableList()
        private val cards = Denomination.values().associateWith { createCards(it) }
        private fun createCards(denomination: Denomination) =
            Suit.values().associateWith { PlayingCard(denomination, it) }
    }
}
