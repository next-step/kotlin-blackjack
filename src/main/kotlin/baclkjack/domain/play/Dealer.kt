package baclkjack.domain.play

class Dealer(private val player: Player = Player("딜러")) : User by player {

    override fun isDraw(): Boolean = score() <= DEFAULT_SCORE

    fun result(players: List<Player>): Map<GameState, Int> = players.map { it.ofGameState(player) }.groupingBy { it }.eachCount()

    companion object {
        const val DEFAULT_SCORE = 16
    }
}
