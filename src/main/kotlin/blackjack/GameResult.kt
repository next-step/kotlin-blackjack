package blackjack

data class GameResult(
    val player: Player,
    val outcome: Outcome,
)

enum class Outcome {
    WIN,
    LOSS,
    DRAW,
    BLACKJACK,
}
