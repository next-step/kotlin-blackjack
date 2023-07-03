package baclkjack.domain.play

class Dealer(name: String = "딜러") : Player(name) {
    override fun isDraw(): Boolean = score() <= DEFAULT_SCORE

    fun result(player: List<Player>): Map<GameState, Int> = player.map { this@Dealer.result(it) }.groupingBy { it }.eachCount()

    companion object {
        const val DEFAULT_SCORE = 16
    }
}
