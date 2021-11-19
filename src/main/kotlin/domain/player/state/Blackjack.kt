package domain.player.state

class Blackjack(other: PlayerState) : Finished(other) {
    override fun earningRate(win: Boolean): Double = if (win) 1.5 else 0.0
    override fun score(): Int = Int.MAX_VALUE
}
