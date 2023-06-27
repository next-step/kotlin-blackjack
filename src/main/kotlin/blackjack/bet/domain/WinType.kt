package blackjack.bet.domain

enum class WinType(
    val payoutRatio: Double
) {
    PLAYER_WIN(1.0),
    PLAYER_BLACK_JACK(0.5),
    DEALER_WIN(-1.0),
    DRAW(0.0),
    DEALER_BUST(1.0),
}
