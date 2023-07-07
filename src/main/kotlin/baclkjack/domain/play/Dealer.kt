package baclkjack.domain.play

class Dealer(private val player: Player = Player("딜러")) : User by player {

    override fun isDraw(): Boolean = score() <= DEFAULT_SCORE

    fun result(players: List<Player>): Int = players.sumOf {
        it.result(this)
    } * DEALER_EARNING_RATE

    companion object {
        const val DEFAULT_SCORE = 16
        const val DEALER_EARNING_RATE = -1

    }
}
