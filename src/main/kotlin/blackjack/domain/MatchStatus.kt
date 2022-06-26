package blackjack.domain

enum class MatchStatus(val revenueRatio: Double) {
    BLACKJACK(1.5),
    WIN(1.0),
    LOSE(-1.0),
    PUSH(0.0)
}
