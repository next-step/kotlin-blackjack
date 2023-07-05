package baclkjack.domain.play

enum class GameState {
    WIN,
    DRAW,
    LOSE;

    companion object {
        fun User.ofGameState(player: User): GameState {
            return when {
                this.burst() -> LOSE
                player.burst() -> WIN
                this.score() > player.score() -> WIN
                this.score() < player.score() -> LOSE
                else -> DRAW
            }
        }
    }
}
