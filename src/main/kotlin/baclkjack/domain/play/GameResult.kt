package baclkjack.domain.play

enum class GameResult(val earningsRate: Double) {
    BLACKJACK(1.5),
    WIN(1.0),
    DRAW(0.0),
    LOSE(-1.0);
}
