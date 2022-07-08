package blackjack.domain.participant

enum class GameResult(
    val magnification: Double
) {
    BLACKJACK_WIN(1.5),
    WIN(1.0),
    DRAW(1.0),
    LOSE(-1.0),
    ;
}
