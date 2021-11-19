package domain.player.state

class Stay(other: PlayerState) : Finished(other) {
    override fun earningRate(): Double = 1.0
    override fun score(): Int = cards.score()
}
