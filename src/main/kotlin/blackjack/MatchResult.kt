package blackjack

enum class MatchResult(
    val message: String,
    val rate: Double
) {
    BLACKJACK("승", 1.5),
    WIN("승", 1.0),
    LOSS("패", -1.0),
    DRAW("무", 0.0)
    ;
}
