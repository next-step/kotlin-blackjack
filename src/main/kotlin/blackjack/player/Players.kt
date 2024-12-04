package blackjack.player

import blackjack.machine.BlackJackMachine

class Players(
    val players: List<Player>,
) {
    companion object {
        fun generateFromNames(playerNames: List<String>) = Players(players = playerNames.map { Player.ready(name = it) })
    }

    fun getWinner(): Player? =
        players
            .filter { it.hand.sum() <= BlackJackMachine.BLACKJACK }
            .maxByOrNull { it.hand.sum() }
}
