package domain.player.state

class Bust(other: PlayerState) : Finished(other) {
    override fun earningRate(): Double = (-1).toDouble()
    override fun score(): Int = Int.MIN_VALUE
}
