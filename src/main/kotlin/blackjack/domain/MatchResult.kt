package blackjack.domain

enum class MatchResult(val text: String, val rate: Double) {
    BLACKJACK_WIN("승", 1.5),
    WIN("승", 1.0),
    LOSS("패", -1.0),
    TIE("무", 1.0)
}
