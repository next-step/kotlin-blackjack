package domain.player.state

class Blackjack(other: PlayerState) : Finished(other) {
    override fun earningRate() = 1.5
    override fun score() = Int.MAX_VALUE
}
