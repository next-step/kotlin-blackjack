package blackjack.view.output

import blackjack.model.PlayRoom
import blackjack.model.player.Player
import blackjack.model.player.PlayerRecords

interface OutputView {
    fun printInitialMessage(playRoom: PlayRoom)

    fun onPlayerHit(player: Player)

    fun printCardsOfPlayer(player: Player, isGameOver: Boolean = false)

    fun printCardsOfPlayRoom(playRoom: PlayRoom, isGameOver: Boolean = false) {
        this.printCardsOfPlayer(playRoom.dealer, isGameOver)
        playRoom.playerBets.forEach { this.printCardsOfPlayer(it.player, isGameOver) }
    }

    fun printPlayerRecords(playerRecords: PlayerRecords)
}
