package blackjack.entity

data class GameResult(
    val player: Participant,
    val earning: Int = 0,
)
