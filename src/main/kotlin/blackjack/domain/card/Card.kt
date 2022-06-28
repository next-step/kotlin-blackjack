package blackjack.domain.card

data class Card(
    val suit: Suit,
    val face: Face,
) {
    fun isAce() = face === Face.ACE

    companion object {
        val ALL_CARDS = Suit.values()
            .flatMap { suit ->
                Face.values().map { Card(suit, it) }
            }
    }
}
