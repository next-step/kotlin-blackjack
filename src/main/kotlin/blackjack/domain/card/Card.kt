package blackjack.domain.card

class Card(
    val suit: Suit,
    val face: Face,
) {
    companion object {
        val ALL_CARDS = Suit.values()
            .flatMap { suit ->
                Face.values().map { Card(suit, it) }
            }
    }
}
