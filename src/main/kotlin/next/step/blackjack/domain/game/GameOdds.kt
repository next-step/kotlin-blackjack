package next.step.blackjack.domain.game

enum class GameOdds(val rate: Double) {
    BLACKJACK(1.5),
    WIN(1.0),
    TIE(0.0),
    LOSE(-1.0);
}