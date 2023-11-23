package blackjack.domain.card

data class Card(
    val suit: Suit,
    val character: Character,
) {
    companion object {
        fun fullCardList() = Suit.values().flatMap { suit ->
            Character.values().map { character ->
                Card(suit, character)
            }
        }
    }
}
