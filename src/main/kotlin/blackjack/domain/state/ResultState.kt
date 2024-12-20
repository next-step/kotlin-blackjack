package blackjack.domain.state

enum class ResultState(val displayMessage: String, val revenueRate: Double) {
    WIN("승", 1.0),
    LOSE("패", -1.0),
    DRAW("무", 0.0),
    BLACKJACK("승", 1.5),
    ;

    fun not(isBlackJack: Boolean): ResultState {
        return when (this) {
            BLACKJACK -> LOSE
            WIN -> LOSE
            LOSE -> if (isBlackJack) BLACKJACK else WIN
            else -> DRAW
        }
    }
}
