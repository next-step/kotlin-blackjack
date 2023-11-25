package blackjack

enum class MatchResult(val profitRate: Float) {
    BLACKJACK_WIN(1.5f),
    WIN(1f),
    DRAW(0f),
    LOSE(-1f)
}
