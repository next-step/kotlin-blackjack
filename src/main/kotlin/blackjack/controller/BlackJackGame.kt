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
    private val dealer = playerProvider.createDealer()

    fun run() {
        val playRoom = PlayRoom(
            cardDistributor = cardDistributor,
            dealer = dealer,
            guests = players
        )
        playRoom.startNewGame()
        outputView?.printInitialMessage(playRoom)
        val records = playRoom.playGame { player ->
            outputView?.onPlayerHit(player)
        }
        outputView?.printCardsOfPlayRoom(playRoom, isGameOver = true)
        outputView?.printPlayerRecords(records)
    }
}
