package domain.player.state

class Stay(other: PlayerState) : Finished(other) {
    override fun earningRate(): Double = (1).toDouble()
    override fun score(): Int = cards.score()
}
