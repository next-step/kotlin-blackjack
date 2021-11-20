package domain.player.state

class Stay(other: PlayerState) : Finished(other) {
    override fun earningRate(win: Boolean): Double = if (win) 1.0 else -1.0
    override fun score(): Int = cards.score()
}
