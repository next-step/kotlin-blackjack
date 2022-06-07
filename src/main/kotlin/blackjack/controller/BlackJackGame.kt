package blackjack.controller

import blackjack.model.CardDistributor
import blackjack.model.DefaultCardDistributor
import blackjack.model.PlayRoom
import blackjack.model.player.PlayerProvider
import blackjack.view.output.OutputView

class BlackJackGame(
    playerProvider: PlayerProvider,
    private val cardDistributor: CardDistributor = DefaultCardDistributor(),
    private val outputView: OutputView? = null
) {

    private val players = playerProvider.createPlayers()

    fun run() {
        val playRoom = PlayRoom(cardDistributor, players)
        playRoom.startNewGame()
        outputView?.printInitialMessage(this.players)
        playRoom.playGame { player ->
            outputView?.printCardsOfPlayer(player, withScore = false)
        }
        outputView?.printCardsOfPlayer(players, withScore = true)
    }
}
