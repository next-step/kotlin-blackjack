package blackjack.domain.enums

enum class MatchResult(
    val match: String
) {
    WIN("승"),
    DRAW("무"),
    LOSE("패")
}