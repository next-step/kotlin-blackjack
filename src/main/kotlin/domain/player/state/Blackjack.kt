package domain.player.state

class Blackjack(other: PlayerState) : Finished(other) {
    override fun earningRate(): Double = 1.5
    override fun score(): Int = Int.MAX_VALUE
}
