package blackjack.domain.state

enum class ResultState(val displayMessage: String, val revenueRate: Double) {
    WIN("승", 1.0),
    LOSE("패", -1.0),
    DRAW("무", 0.0),
    ;

    fun not(): ResultState {
        return when (this) {
            WIN -> LOSE
            LOSE -> WIN
            else -> DRAW
        }
    }
}
