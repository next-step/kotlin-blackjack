package blackjack

data class Player(
    val name: String,
    val hand: Hand = Hand()
)
