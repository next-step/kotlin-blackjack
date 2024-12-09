package blackjack

data class GameResult2(
    val player: Player,
    val outcome: Outcome,
)

enum class Outcome {
    WIN,
    LOSS,
    PUSH,
    BLACKJACK,
}
