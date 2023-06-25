package next.step.blackjack.domain

import next.step.blackjack.domain.player.Player
import next.step.blackjack.domain.player.PlayerName
import next.step.blackjack.domain.player.PlayerNames

data class GameBoard(val gameCards: GameCards, val players: Set<Player>) {
    fun start(announce: (Set<Player>, Int) -> Unit) {
        announce(players, Player.INIT_CARD_CNT)
    }

    fun turn(chooseHit: (Player) -> Boolean, announce: (Player) -> Unit) {
        players.forEach {
            while (it.canHit() && chooseHit(it)) {
                hit(it)
                announce(it)
            }
        }
    }

    private fun hit(player: Player) {
        player.hit(gameCards.pop())
    }

    fun finish(announce: (Set<Player>) -> Unit) {
        announce(players)
    }

    companion object {
        fun of(gameCards: GameCards, playerNames: PlayerNames): GameBoard {
            return GameBoard(gameCards, players(playerNames, gameCards))
        }

        private fun players(playerNames: PlayerNames, gameCards: GameCards) =
            playerNames.map { player(it, gameCards) }.toSet()

        private fun player(name: PlayerName, gameCards: GameCards) =
            Player.of(name) { gameCards.pop(it) }
    }
}
