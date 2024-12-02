package blackjack.entity

data class GameResult(
    val player: Participant,
    val wins: Int = 0,
    val loses: Int = 0,
    val draws: Int = 0,
)
