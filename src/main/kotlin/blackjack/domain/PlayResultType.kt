package blackjack.domain

enum class PlayResultType(val typeName: String, val rateOfReturn: Double = 0.0) {
    WIN("승", 1.0),
    BLACKJACK("승", 1.5),
    LOSE("패", -1.0),
    DRAW("무"),
}
