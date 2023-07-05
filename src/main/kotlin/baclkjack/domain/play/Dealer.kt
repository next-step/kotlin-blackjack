package baclkjack.domain.play

import baclkjack.domain.play.GameState.Companion.ofGameState

class Dealer(player: Player = Player("딜러")) : User by player {

    override fun isDraw(): Boolean = score() <= DEFAULT_SCORE

    fun result(players: List<User>): Map<GameState, Int> = players.map { ofGameState(it) }.groupingBy { it }.eachCount()

    companion object {
        const val DEFAULT_SCORE = 16
    }
}
