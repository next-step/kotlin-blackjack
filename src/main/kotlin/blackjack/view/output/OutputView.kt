package blackjack.view.output

import blackjack.model.PlayRoom
import blackjack.model.player.Player
import blackjack.model.player.PlayerRecords

interface OutputView {
    fun printInitialMessage(playRoom: PlayRoom)

    fun onPlayerHit(player: Player)

    fun printCardsOfPlayer(player: Player, withScore: Boolean = false)

    fun printCardsOfPlayer(playRoom: PlayRoom, withScore: Boolean = false) {
        this.printCardsOfPlayer(playRoom.dealer, withScore)
        playRoom.guests.forEach { this.printCardsOfPlayer(it, withScore) }
    }

    fun printPlayerRecords(playerRecords: PlayerRecords)
}
