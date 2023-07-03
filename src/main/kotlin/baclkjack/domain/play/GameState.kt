package baclkjack.domain.play

enum class GameState {
    WIN,
    DRAW,
    LOSE;

    companion object {
        fun Player.ofGameState(dealer: Player): GameState {
            return when {
                this.burst() -> LOSE
                dealer.burst() -> WIN
                this.score() > dealer.score() -> WIN
                this.score() < dealer.score() -> LOSE
                else -> DRAW
            }
        }
    }
}
