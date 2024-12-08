package blackjack.domain

enum class MatchResult(val description: String) {
    WIN("승"),
    LOSS("패"),
    DRAW("무"),
}
