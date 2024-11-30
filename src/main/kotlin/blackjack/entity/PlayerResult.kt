package blackjack.entity

data class PlayerResult(
    val playerName: String,
    val playHand: Hand,
    val score: Int,
)
