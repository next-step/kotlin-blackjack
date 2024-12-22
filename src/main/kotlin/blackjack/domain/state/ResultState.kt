package blackjack.domain.state

enum class ResultState(val displayMessage: String, val revenueRate: Double) {
    WIN("승", 1.0),
    LOSE("패", -1.0),
    PUSH("무", 0.0),
    BLACKJACK("승", 1.5),
}
