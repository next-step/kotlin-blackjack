package domain.participant

enum class PlayerWinType(val displayName: String, val profitMultiplier: Double) {
    BLACKJACK_WIN("승", 1.5),
    WIN("승", 1.0),
    LOSE("패", -1.0),
    DRAW("무", 0.0),
}
