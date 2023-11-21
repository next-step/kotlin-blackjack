package blackJack.view

import blackJack.model.Player

object OutputView {
    private const val PLAYER_STATE_FORMAT = "%s카드 : %s"
    private const val PLAYER__FINAL_STATE_FORMAT = "%s카드 : %s - 결과: %s"

    fun printPlayersState(players: List<Player>) {
        players.forEach { printPlayerState(it) }
    }

    fun printFinalState(players: List<Player>) {
        players.forEach { printPlayerFinalState(it) }
    }

    fun printPlayerState(player: Player) {
        player.hand
            .joinToString { it.rank.symbol + it.suit.symbol }
            .let { println(PLAYER_STATE_FORMAT.format(player.name, it)) }
    }

    private fun printPlayerFinalState(player: Player) {
        player.hand
            .joinToString { it.rank.symbol + it.suit.symbol }
            .let { println(PLAYER__FINAL_STATE_FORMAT.format(player.name, it, player.calculateScore())) }
    }
}
