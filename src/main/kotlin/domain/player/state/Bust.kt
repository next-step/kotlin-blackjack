package domain.player.state

class Bust(other: PlayerState) : Finished(other) {
    override fun earningRate() = (-1).toDouble()
    override fun score() = Int.MIN_VALUE
}
