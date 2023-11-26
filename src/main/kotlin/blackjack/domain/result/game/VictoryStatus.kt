package blackjack.domain.result.game

enum class VictoryStatus {
    WIN,
    LOSS,
    PUSH;

    val opponentResult: VictoryStatus
        get() = when (this) {
            WIN -> LOSS
            PUSH -> PUSH
            LOSS -> WIN
        }
}
