package domain.player.state

class Stay(other: PlayerState) : Finished(other) {
    override fun earningRate() = (1).toDouble()
    override fun score() = cards.score()
}
