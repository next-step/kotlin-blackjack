package card

data class PlayingCard private constructor(val suit: Suit, val denomination: Denomination) {
    companion object {
        val ALL =
            Suit.entries.flatMap { suit ->
                Denomination.entries.map { denomination -> PlayingCard(suit, denomination) }
            }

        fun of(
            suit: Suit,
            denomination: Denomination,
        ): PlayingCard {
            return ALL.find { it.suit == suit && it.denomination == denomination } ?: throw IllegalArgumentException()
        }
    }
}
